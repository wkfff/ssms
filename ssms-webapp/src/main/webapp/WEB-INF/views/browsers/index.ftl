<@layout.extends name="../_layouts/base.ftl">
    <@layout.put block="head">
    <style type="text/css">
        body, td {
            font-size: 12px;
            color: #333;
            text-align: center;
        }

        a {
            text-decoration: none;
        }

        table {
            width: 400px;
        }

        td {
            border: 1px dashed;
            text-align: left;
            vertical-align: middle;
        }

        img {
            vertical-align: middle;
            width: 16px;
            height: 16px;
        }

        #ie6-warning {
            background: rgb(255, 255, 225) no-repeat scroll 3px center;
            width: 97%;
            padding: 2px 15px 2px 23px;
        }
    </style>
    </@>
    <@layout.put block="contents">
    <div id="ie6-warning">您正在使用 Internet Explorer 6/7。建议您升级到
        <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器：
        <a href="http://www.mozillaonline.com/">Firefox</a> /
        <a href="http://www.google.com/chrome/?hl=zh-CN">Chrome</a> /
        <a href="http://www.apple.com.cn/safari/">Safari</a> / <a href="http://www.operachina.com/">Opera</a>
    </div>
    <p>您也使用以下的国产浏览器(排名不分先后):</p>
    <p>
        <img src="http://se.360.cn/favicon.ico" alt=""/><a href="http://se.360.cn/">360安全浏览器</a>
        /
        <img src="http://www.liebao.cn/favicon.ico" alt=""/><a href="http://www.liebao.cn/">猎豹安全浏览器</a>
        /
        <img src="http://browser.qq.com/favicon.ico" alt=""/><a href="http://browser.qq.com/">QQ浏览器</a>
    </p>
    </@>
</@>