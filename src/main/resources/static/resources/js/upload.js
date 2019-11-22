function uploadExcel(files) {
    layui.use(['excel', 'layer'], function () {
        var excel = layui.excel
        var layer = layui.layer
        try {
            excel.importExcel(files, {
                // 读取数据的同时梳理数据
                fields: {
                    'id': 'A'
                    , 'username': 'B'
                    , 'experience': 'C'
                    , 'sex': 'D'
                    , 'score': 'E'
                    , 'city': 'F'
                    , 'classify': 'G'
                    , 'wealth': 'H'
                    , 'sign': 'I'
                }
            }, function (data) {
                // 还可以再进行数据梳理
                /*						data = excel.filterImportData(data, {
                        'id': 'A'
                        ,'username': 'B'
                        ,'experience': 'C'
                        ,'sex': 'D'
                        ,'score': 'E'
                        ,'city': 'F'
                        ,'classify': 'G'
                        ,'wealth': 'H'
                        ,'sign': 'I'
                      });
                */
                // 如果不需要展示直接上传，可以再次 $.ajax() 将JSON数据通过 JSON.stringify() 处理后传递到后端即可
                layer.open({
                    title: '文件转换结果'
                    , area: ['800px', '400px']
                    , tipsMore: true
                    , content: laytpl($('#LAY-excel-export-ans').html()).render({data: data, files: files})
                    , success: function () {
                        element.render('tab')
                        layui.code({})
                    }
                })
            })
        } catch (e) {
            layer.alert(e.message)
        }
    })
}