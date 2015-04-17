<#import "/layout/_rec.ftl" as layout/>
<@layout.doLayout >
    可上传
    <@layout.upload id="up1" module="demo" sid=123/>
    只读
    <@layout.upload id="up2" module="demo" sid=123 readonly=true/>
</@layout.doLayout>