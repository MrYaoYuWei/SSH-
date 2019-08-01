$(function () {
    layui.use(['layer', 'jquery', 'form', 'element'], function () {
        var layer = layui.layer
            , $ = layui.jquery
            , form = layui.form
            , element = layui.element;
        var testEditor = editormd("test-editormd", {
            height: '385px',
            syncScrolling: "single",
            theme: "default",
            previewTheme: "default",
            editorTheme: "default",
            saveHTMLToTextarea: true,
            searchReplace: true,
            emoji: true,
            taskList: true,
            tocm: true,
            tex: true,
            flowChart: true,
            sequenceDiagram: true,
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "/haha/blog/uploadfile",
            path: "/haha/static/editormd/lib/"
        })
    })
})