# 图片审核
https://developer.qiniu.com/censor/api/5588/image-censor

# 调用示例
python ImageReview.py < censor | pulp | terror | politician | ads > < url >

```
python ImageReview.py censor https://mars-assets.qnssl.com/resource/gogopher.jpg
{
    "reqid": "IisAABW7lT5croUV, IisAABW7lT5croUV",
    "code": 200,
    "xlog": "COMMON-GATE:255;AI:256"
}
{
    "message": "OK",
    "code": 200,
    "result": {
        "scenes": {
            "terror": {
                "details": [
                    {
                        "score": 0.99985,
                        "suggestion": "pass",
                        "label": "normal"
                    }
                ],
                "suggestion": "pass"
            },
            "politician": {
                "suggestion": "pass"
            },
            "pulp": {
                "details": [
                    {
                        "score": 0.65191,
                        "suggestion": "pass",
                        "label": "normal"
                    }
                ],
                "suggestion": "pass"
            }
        },
        "suggestion": "pass"
    }
}
```
