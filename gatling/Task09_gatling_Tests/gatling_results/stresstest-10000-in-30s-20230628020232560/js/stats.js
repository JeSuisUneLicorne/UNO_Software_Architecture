var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "20000",
        "ok": "19707",
        "ko": "293"
    },
    "minResponseTime": {
        "total": "0",
        "ok": "0",
        "ko": "0"
    },
    "maxResponseTime": {
        "total": "225",
        "ok": "225",
        "ko": "12"
    },
    "meanResponseTime": {
        "total": "17",
        "ok": "17",
        "ko": "1"
    },
    "standardDeviation": {
        "total": "28",
        "ok": "28",
        "ko": "1"
    },
    "percentiles1": {
        "total": "5",
        "ok": "5",
        "ko": "1"
    },
    "percentiles2": {
        "total": "15",
        "ok": "15",
        "ko": "1"
    },
    "percentiles3": {
        "total": "82",
        "ok": "83",
        "ko": "2"
    },
    "percentiles4": {
        "total": "122",
        "ok": "123",
        "ko": "9"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 19707,
    "percentage": 99
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 293,
    "percentage": 1
},
    "meanNumberOfRequestsPerSecond": {
        "total": "645.161",
        "ok": "635.71",
        "ko": "9.452"
    }
},
contents: {
"req_save-43781": {
        type: "REQUEST",
        name: "save",
path: "save",
pathFormatted: "req_save-43781",
stats: {
    "name": "save",
    "numberOfRequests": {
        "total": "10000",
        "ok": "9751",
        "ko": "249"
    },
    "minResponseTime": {
        "total": "0",
        "ok": "1",
        "ko": "0"
    },
    "maxResponseTime": {
        "total": "225",
        "ok": "225",
        "ko": "12"
    },
    "meanResponseTime": {
        "total": "29",
        "ok": "30",
        "ko": "1"
    },
    "standardDeviation": {
        "total": "34",
        "ok": "34",
        "ko": "1"
    },
    "percentiles1": {
        "total": "12",
        "ok": "13",
        "ko": "1"
    },
    "percentiles2": {
        "total": "45",
        "ok": "47",
        "ko": "1"
    },
    "percentiles3": {
        "total": "98",
        "ok": "100",
        "ko": "2"
    },
    "percentiles4": {
        "total": "152",
        "ok": "153",
        "ko": "9"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 9751,
    "percentage": 98
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 249,
    "percentage": 2
},
    "meanNumberOfRequestsPerSecond": {
        "total": "322.581",
        "ok": "314.548",
        "ko": "8.032"
    }
}
    },"req_load-ec4d1": {
        type: "REQUEST",
        name: "load",
path: "load",
pathFormatted: "req_load-ec4d1",
stats: {
    "name": "load",
    "numberOfRequests": {
        "total": "10000",
        "ok": "9956",
        "ko": "44"
    },
    "minResponseTime": {
        "total": "0",
        "ok": "0",
        "ko": "0"
    },
    "maxResponseTime": {
        "total": "136",
        "ok": "136",
        "ko": "1"
    },
    "meanResponseTime": {
        "total": "5",
        "ok": "5",
        "ko": "1"
    },
    "standardDeviation": {
        "total": "10",
        "ok": "10",
        "ko": "0"
    },
    "percentiles1": {
        "total": "2",
        "ok": "2",
        "ko": "1"
    },
    "percentiles2": {
        "total": "4",
        "ok": "4",
        "ko": "1"
    },
    "percentiles3": {
        "total": "13",
        "ok": "13",
        "ko": "1"
    },
    "percentiles4": {
        "total": "66",
        "ok": "66",
        "ko": "1"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 9956,
    "percentage": 100
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 44,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "322.581",
        "ok": "321.161",
        "ko": "1.419"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
