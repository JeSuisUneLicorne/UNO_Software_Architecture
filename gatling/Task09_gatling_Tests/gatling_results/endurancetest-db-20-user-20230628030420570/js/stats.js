var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "2000",
        "ok": "1209",
        "ko": "791"
    },
    "minResponseTime": {
        "total": "4",
        "ok": "12",
        "ko": "4"
    },
    "maxResponseTime": {
        "total": "9096",
        "ok": "8457",
        "ko": "9096"
    },
    "meanResponseTime": {
        "total": "602",
        "ok": "588",
        "ko": "625"
    },
    "standardDeviation": {
        "total": "1277",
        "ok": "1224",
        "ko": "1355"
    },
    "percentiles1": {
        "total": "72",
        "ok": "80",
        "ko": "64"
    },
    "percentiles2": {
        "total": "287",
        "ok": "323",
        "ko": "200"
    },
    "percentiles3": {
        "total": "3459",
        "ok": "3559",
        "ko": "3277"
    },
    "percentiles4": {
        "total": "6316",
        "ok": "5840",
        "ko": "6517"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 985,
    "percentage": 49
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 48,
    "percentage": 2
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 176,
    "percentage": 9
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 791,
    "percentage": 40
},
    "meanNumberOfRequestsPerSecond": {
        "total": "17.699",
        "ok": "10.699",
        "ko": "7"
    }
},
contents: {
"req_savedb-2dc6a": {
        type: "REQUEST",
        name: "saveDB",
path: "saveDB",
pathFormatted: "req_savedb-2dc6a",
stats: {
    "name": "saveDB",
    "numberOfRequests": {
        "total": "1000",
        "ok": "1000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "12",
        "ok": "12",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2379",
        "ok": "2379",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "134",
        "ok": "134",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "202",
        "ok": "202",
        "ko": "-"
    },
    "percentiles1": {
        "total": "61",
        "ok": "61",
        "ko": "-"
    },
    "percentiles2": {
        "total": "149",
        "ok": "149",
        "ko": "-"
    },
    "percentiles3": {
        "total": "479",
        "ok": "479",
        "ko": "-"
    },
    "percentiles4": {
        "total": "916",
        "ok": "916",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 980,
    "percentage": 98
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 14,
    "percentage": 1
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 6,
    "percentage": 1
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "8.85",
        "ok": "8.85",
        "ko": "-"
    }
}
    },"req_loaddb-37404": {
        type: "REQUEST",
        name: "loadDB",
path: "loadDB",
pathFormatted: "req_loaddb-37404",
stats: {
    "name": "loadDB",
    "numberOfRequests": {
        "total": "1000",
        "ok": "209",
        "ko": "791"
    },
    "minResponseTime": {
        "total": "4",
        "ok": "644",
        "ko": "4"
    },
    "maxResponseTime": {
        "total": "9096",
        "ok": "8457",
        "ko": "9096"
    },
    "meanResponseTime": {
        "total": "1071",
        "ok": "2761",
        "ko": "625"
    },
    "standardDeviation": {
        "total": "1668",
        "ok": "1660",
        "ko": "1355"
    },
    "percentiles1": {
        "total": "92",
        "ok": "2179",
        "ko": "64"
    },
    "percentiles2": {
        "total": "1921",
        "ok": "3682",
        "ko": "200"
    },
    "percentiles3": {
        "total": "4868",
        "ok": "6258",
        "ko": "3277"
    },
    "percentiles4": {
        "total": "6841",
        "ok": "7186",
        "ko": "6517"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 5,
    "percentage": 1
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 34,
    "percentage": 3
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 170,
    "percentage": 17
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 791,
    "percentage": 79
},
    "meanNumberOfRequestsPerSecond": {
        "total": "8.85",
        "ok": "1.85",
        "ko": "7"
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
