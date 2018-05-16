/**
 * 判断浏览器是否支持flash
 * @returns {flashChecker.commonAnonym$1}
 */
function flashChecker() {
    var hasFlash = 0; //是否安装了flash
    var flashVersion = 0; //flash版本
    if (document.all) {
        var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
        if (swf) {
            hasFlash = 1;
            VSwf = swf.GetVariable("$version");
            flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);
        }
    } else {
        if (navigator.plugins && navigator.plugins.length > 0) {
            var swf = navigator.plugins["Shockwave Flash"];
            if (swf) {
                hasFlash = 1;
                var words = swf.description.split(" ");
                for (var i = 0; i < words.length; ++i) {
                    if (isNaN(parseInt(words[i])))
                        continue;
                    flashVersion = parseInt(words[i]);
                }
            }
        }
    }
    return {f: hasFlash, v: flashVersion};
}

/**
 * 判断浏览器是否是ie
 * @returns {Boolean}
 */
function get_browser_ie_6789()
{
    var browser = navigator.appName
    var b_version = navigator.appVersion
    var version = b_version.split(";");
    var trim_Version = version[1].replace(/[ ]/g, "");
    if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE6.0")
    {
        return true;
    } else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE7.0")
    {
        return true;
    } else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE8.0")
    {
        return true;
    } else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE9.0")
    {
        return true;
    }
    return false;
}
var uploadify_config = {
    auto:true, 
    //接受true or false两个值，当为true时选择文件后会自动上传；为false时只会把选择的文件增加进队列但不会上传，这时只能使用upload的方法触发上传。不设置auto时默认为true
    buttonClass: "some-class", 
    //设置上传按钮的class
    buttonCursor: 'hand',
    //设置鼠标移到按钮上的开状，接受两个值'hand'和'arrow'(手形和箭头)
    buttonText: '<div>选择文件</div>',
    //设置按钮文字。值会被当作html渲染，所以也可以包含html标签
    debug: false,
    //开启或关闭debug模式
    fileObjName:'upload_file',
    //设置在后台脚本使用的文件名。举个例子，在php中，如果这个选项设置为'the_files',你可以使用$_FILES['the_files']存取这个已经上传的文件。
    fileSizeLimit:'200MB',
    //设置上传文件的容量最大值。这个值可以是一个数字或者字符串。如果是字符串，接受一个单位（B,KB,MB,GB）。如果是数字则默认单位为KB。设置为0时表示不限制
    fileTypeExts: '*.*',
    //设置允许上传的文件扩展名（也就是文件类型）。但手动键入文件名可以绕过这种级别的安全检查，所以你应该始终在服务端中检查文件类型。输入多个扩展名时用分号隔开('*.jpg;*.png;*.gif')
    fileTypeDesc: 'All Files',
    //可选文件的描述。这个值出现在文件浏览窗口中的文件类型下拉选项中。（chrome下不支持，会显示为'自定义文件',ie and firefox下可显示描述）
    formData: {},
    //通过get或post上传文件时，此对象提供额外的数据。如果想动态设置这些值，必须在onUploadStartg事件中使用settings的方法设置。在后台脚本中使用 $_GET or $_POST arrays (PHP)存取这些值。看官网参考写法：http://www.uploadify.com/documentation/uploadify/formdata/
    height: 30,
    //设置按钮的高度(单位px)，默认为30.(不要在值里写上单位，并且要求一个整数，width也一样)
    width: 120,
    //设置按钮宽度(单位px)，默认120
    itemTemplate:false,
    //模板对象。给增加到上传队列中的每一项指定特殊的html模板。模板格式请看官网http://www.uploadify.com/documentation/uploadify/itemtemplate/
    method:'post',
    //提交上传文件的方法，接受post或get两个值，默认为post
    multi: false,
    //设置是否允许一次选择多个文件，true为允许，false不允许
    overrideEvents: [],
    //重写事件，接受事件名称的数组作为参数。所设置的事件将可以被用户重写覆盖
    preventCaching:true,
    //是否缓存swf文件。默认为true，会给swf的url地址设置一个随机数，这样它就不会被缓存。(有些浏览器缓存了swf文件就会触发不了里面的事件--by rainweb)
    progressData: 'percentage',
    //设置文件上传时显示数据，有‘percentage’ or ‘speed’两个参数(百分比和速度)
    queueID: false,
    //设置上传队列DOM元素的ID，上传的项目会增加进这个ID的DOM中。设置为false时则会自动生成队列DOM和ID。默认为false
    queueSizeLimit: 999,
    //设置每一次上传队列中的文件数量。注意并不是限制总的上传文件数量（那是uploadLimit）.如果增加进队列中的文件数量超出这个值，将会触发onSelectError事件。默认值为999
    removeCompleted: true,
    //是否移除掉队列中已经完成上传的文件。false为不移除
    removeTimeout: 3,
    //设置上传完成后删除掉文件的延迟时间，默认为3秒。如果removeCompleted为false的话，就没意义了
    requeueErrors: false,
    //设置上传过程中因为出错导致上传失败的文件是否重新加入队列中上传
    successTimeout: 30,
    //设置文件上传后等待服务器响应的秒数，超出这个时间，将会被认为上传成功，默认为30秒
    swf: 'swfupload/uploadify/uploadify.swf',
    //swf的相对路径，必写项
    uploader: httpCom+'file/upload',
    //服务器端脚本文件路径，必写项
    uploadLimit: 999,
    //上传文件的数量。达到或超出这数量会触发onUploadError方法。默认999
    onUploadSuccess: function(file,data,respone){

    },
    onUploadError : function(file, errorCode, errorMsg, errorString) {
    	var html  = '上传文件失败:'+file.name;
    	html += "错误代码:"+errorCode+"\r\n";
    	html += "错误 信息:"+errorString+"\r\n";;
        alert(html);
    }
};