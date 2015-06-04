/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ModelExt.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@SuppressWarnings("serial")
public class ModelExt<M extends ModelExt<M>> extends Model<M> {

    protected String deleteColumnLabel = "deleteflag";

    private String version = "version";


    private static List<CallbackListener> callbackListeners = Lists.newArrayList();

    private Class<? extends ModelExt<M>> clazz;

    @SuppressWarnings("unchecked")
    public ModelExt() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        clazz = (Class<? extends ModelExt<M>>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }

    public static void addCallbackListener(CallbackListener callbackListener) {
        callbackListeners.add(callbackListener);
    }

    /** 标记是否伪删除，如果要做伪删除则重载该方法。 */
    public boolean pseudoDelete() {
        return false;
    }

    @Override
    public boolean save() {
        for (CallbackListener callbackListener : callbackListeners) {
            callbackListener.beforeSave(this);
        }
        Table tableInfo = TableMapping.me().getTable(clazz);
        if (pseudoDelete()) {
            if (!tableInfo.hasColumnLabel(deleteColumnLabel)) {
                throw new ActiveRecordException("The deleteColumnLabel (" + deleteColumnLabel + ") is not exist");
            }
            this.set(deleteColumnLabel, 0);
        }
        boolean result = super.save();
        for (CallbackListener callbackListener : callbackListeners) {
            callbackListener.afterSave(this);
        }
        return result;
    }

    @Override
    public boolean update() {
        for (CallbackListener callbackListener : callbackListeners) {
            callbackListener.beforeUpdate(this);
        }
        boolean result = super.update();
        for (CallbackListener callbackListener : callbackListeners) {
            callbackListener.afterUpdate(this);
        }
        return result;
    }

    @Override
    public boolean delete() {
        Table tableInfo = TableMapping.me().getTable(clazz);
        for (CallbackListener callbackListener : callbackListeners) {
            callbackListener.beforeDelete(this);
        }
        boolean result;
        if (pseudoDelete()) {
            if (!tableInfo.hasColumnLabel(deleteColumnLabel)) {
                throw new ActiveRecordException("The deleteColumnLabel (" + deleteColumnLabel + ") is not exist");
            }
            this.set(deleteColumnLabel, 1);
            result = this.update();
        } else {
            result = super.delete();
        }
        for (CallbackListener callbackListener : callbackListeners) {
            callbackListener.afterDelete(this);
        }
        return result;

    }

    @Override
    public boolean deleteById(Object id) {
        Table tableInfo = TableMapping.me().getTable(clazz);
        for (CallbackListener callbackListener : callbackListeners) {
            callbackListener.beforeDelete(this);
        }
        boolean result ;
        if (pseudoDelete()) {
            if (!tableInfo.hasColumnLabel(deleteColumnLabel)) {
                throw new ActiveRecordException("The deleteColumnLabel (" + deleteColumnLabel + ") is not exist");
            }
            String pKey = tableInfo.getPrimaryKey();
            if (id == null)
                throw new ActiveRecordException("You can't update model without Primary Key.");
            String sql = "update "+tableInfo.getName()+" set "+deleteColumnLabel+" = 1 where "+pKey+" = ?";
            result = Db.update(sql,id)>=1;
        } else {
            result = super.deleteById(id);
        }
        for (CallbackListener callbackListener : callbackListeners) {
            callbackListener.afterDelete(this);
        }
        return result;
    }

    public int deleteAll() {
        String primaryKey = TableMapping.me().getTable(clazz).getPrimaryKey();
        return Db.update("delete from " + tableName() + " where " + primaryKey + "=?");
    }

    public int deleteByColumn(String column, Object value) {
        return deleteByColumns(Lists.newArrayList(column), Lists.newArrayList(value));
    }

    public int deleteByColumns(List<String> columns, List<Object> values) {
        Preconditions.checkArgument(columns.size() > 0, "columns is empty");
        Preconditions.checkArgument(values.size() > 0, "values is empty");
        Preconditions.checkArgument(values.size() == columns.size(), "column size != values size");
        String sql="";
        Table tableInfo = TableMapping.me().getTable(clazz);
        if (pseudoDelete()) {
            if (!tableInfo.hasColumnLabel(deleteColumnLabel)) {
                throw new ActiveRecordException("The deleteColumnLabel (" + deleteColumnLabel + ") is not exist");
            }
            String pKey = tableInfo.getPrimaryKey();
            sql+= "update "+tableInfo.getName()+" set "+deleteColumnLabel+" = 1";
        }else{
            sql+= "delete from " + tableInfo.getName() ;
        }
        sql+=" where 1=1";
        for (String column : columns) {
            sql += " and " + column + " = ?";
        }
        return Db.update(sql, values.toArray());
    }

    public List<M> findAll() {
        String sql ="select * from " + tableName();
        if(pseudoDelete()){
            sql+=" where "+deleteColumnLabel+" is null or "+deleteColumnLabel+" !=1 ";
        }
        return find(sql);
    }

    public List<M> findBySecurity() {
        String sql ="select * from " + tableName();
        if(pseudoDelete()){
            sql+=" where "+deleteColumnLabel+" is null or "+deleteColumnLabel+" !=1 ";
        }
        return find(sql);
    }

    public M findFirstByColumn(String column, Object value) {
        List<M> result = findByColumns(Lists.newArrayList(column), Lists.newArrayList(value));
        return result.size() > 0 ? result.get(0) : null;
    }

    public List<M> findByColumn(String column, Object value) {
        return findByColumns(Lists.newArrayList(column), Lists.newArrayList(value));
    }

    public M findFirstByColumns(List<String> columns, List<Object> values) {
        List<M> result = findByColumns(columns, values);
        return result.size() > 0 ? result.get(0) : null;
    }

    public List<M> findByColumns(List<String> columns, List<Object> values) {
        Preconditions.checkArgument(columns.size() > 0, "columns is empty");
        Preconditions.checkArgument(values.size() > 0, "values is empty");
        Preconditions.checkArgument(values.size() == columns.size(), "column size != values size");
        String sql = "select * from " + tableName() + " where 1=1";
        if(pseudoDelete()){
            sql += " and "+deleteColumnLabel+" is null or "+deleteColumnLabel+" !=1 ";
        }
        for (String column : columns) {
            sql += " and " + column + " = ?";
        }
        return find(sql, values.toArray());
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<M> children(Class<? extends Model> model) {
        Parent child = model.getAnnotation(Parent.class);
        String foreignKey = child.foreignKey();
        Class<? extends Model> childModel = child.model();
        String childTableName = TableMapping.me().getTable(childModel).getName();
        String primaryKey = TableMapping.me().getTable(clazz).getPrimaryKey();
        try {
            return childModel.newInstance().find("select * from " + childTableName + " where " + foreignKey + "= ?",
                    get(primaryKey));
        } catch (Exception e) {
            throw new ActiveRecordException(e.getMessage(), e);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public M parent(Class<? extends Model> model) {
        Parent parent = model.getAnnotation(Parent.class);
        String foreignKey = parent.foreignKey();
        Class<? extends Model> parentModel = parent.model();
        String parentTableName = TableMapping.me().getTable(parentModel).getName();
        String primaryKey = TableMapping.me().getTable(clazz).getPrimaryKey();
        try {
            return (M) parentModel.newInstance().findFirst(
                    "select * from " + parentTableName + " where " + foreignKey + "= ?", get(primaryKey));
        } catch (Exception e) {
            throw new ActiveRecordException(e.getMessage(), e);
        }
    }

    private String tableName() {
        return TableMapping.me().getTable(clazz).getName();
    }
}
