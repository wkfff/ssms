<#import "/layout/_layout.ftl" as layout/>

<@layout.layout>
<h1>123</h1>
<h2>演示插值abc：${abc}</h2>
<h2>演示插值zzz：${zzz!}</h2>
<h2>演示插值sessionVar：${sessionVar!?datetime}</h2>
zzz值来自请求的参数
<h2>演示插值def：${def}</h2>
<h2>演示插值aaa：${aaa}</h2>
</@layout.layout>