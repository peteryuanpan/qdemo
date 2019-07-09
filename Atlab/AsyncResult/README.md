#  通过job_id获取视频审核结果
https://developer.qiniu.com/censor/api/5620/video-censor#3

# PYTHON
python AsyncResult.py < jobid >

```
python AsyncResult.py 5c47f321984fe60008eb9507
{
    "status": "FINISHED",
    "vid": "",
    "rescheduled_at": "2019-01-23T12:52:49.171+08:00",
    "created_at": "2019-01-23T12:52:49.171+08:00",
    "request": {
        "params": {
            "sync": false,
            "scenes": [
                "pulp",
                "terror",
                "politician"
            ]
        },
        "data": {
            "uri": "https://mars-assets.qnssl.com/scene.mp4"
        }
    },
    "updated_at": "2019-01-23T12:52:54.014+08:00",
    "result": {
        "message": "OK",
        "code": 200,
        "result": {
            "scenes": {
                "terror": {
                    "suggestion": "pass",
                    "cuts": [
                        {
                            "details": [
                                {
                                    "score": 0.99982667,
                                    "suggestion": "pass",
                                    "label": "normal"
                                }
                            ],
                            "suggestion": "pass",
                            "offset": 520
                        },
                        {
                            "details": [
                                {
                                    "score": 0.98993665,
                                    "suggestion": "pass",
                                    "label": "normal"
                                }
                            ],
                            "suggestion": "pass",
                            "offset": 5520
                        }
                    ]
                },
                "politician": {
                    "suggestion": "pass",
                    "cuts": [
                        {
                            "suggestion": "pass",
                            "offset": 520
                        },
                        {
                            "suggestion": "pass",
                            "offset": 5520
                        }
                    ]
                },
                "pulp": {
                    "suggestion": "pass",
                    "cuts": [
                        {
                            "details": [
                                {
                                    "score": 0.99995667,
                                    "suggestion": "pass",
                                    "label": "normal"
                                }
                            ],
                            "suggestion": "pass",
                            "offset": 520
                        },
                        {
                            "details": [
                                {
                                    "score": 0.97488004,
                                    "suggestion": "pass",
                                    "label": "normal"
                                }
                            ],
                            "suggestion": "pass",
                            "offset": 5520
                        }
                    ]
                }
            },
            "suggestion": "pass"
        }
    },
    "id": "5c47f321984fe60008eb9507"
}
```
