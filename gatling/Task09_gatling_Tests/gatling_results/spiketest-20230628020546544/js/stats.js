var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "640",
        "ok": "508",
        "ko": "132"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "2",
        "ko": "18"
    },
    "maxResponseTime": {
        "total": "228",
        "ok": "228",
        "ko": "158"
    },
    "meanResponseTime": {
        "total": "69",
        "ok": "69",
        "ko": "71"
    },
    "standardDeviation": {
        "total": "53",
        "ok": "56",
        "ko": "35"
    },
    "percentiles1": {
        "total": "54",
        "ok": "51",
        "ko": "63"
    },
    "percentiles2": {
        "total": "103",
        "ok": "104",
        "ko": "95"
    },
    "percentiles3": {
        "total": "167",
        "ok": "187",
        "ko": "139"
    },
    "percentiles4": {
        "total": "212",
        "ok": "216",
        "ko": "148"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 508,
    "percentage": 79
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
    "count": 132,
    "percentage": 21
},
    "meanNumberOfRequestsPerSecond": {
        "total": "32",
        "ok": "25.4",
        "ko": "6.6"
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
        "total": "320",
        "ok": "188",
        "ko": "132"
    },
    "minResponseTime": {
        "total": "6",
        "ok": "6",
        "ko": "18"
    },
    "maxResponseTime": {
        "total": "228",
        "ok": "228",
        "ko": "158"
    },
    "meanResponseTime": {
        "total": "93",
        "ok": "108",
        "ko": "71"
    },
    "standardDeviation": {
        "total": "52",
        "ok": "57",
        "ko": "35"
    },
    "percentiles1": {
        "total": "88",
        "ok": "102",
        "ko": "63"
    },
    "percentiles2": {
        "total": "127",
        "ok": "138",
        "ko": "95"
    },
    "percentiles3": {
        "total": "201",
        "ok": "210",
        "ko": "139"
    },
    "percentiles4": {
        "total": "219",
        "ok": "223",
        "ko": "148"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 188,
    "percentage": 59
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
    "count": 132,
    "percentage": 41
},
    "meanNumberOfRequestsPerSecond": {
        "total": "16",
        "ok": "9.4",
        "ko": "6.6"
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
        "total": "320",
        "ok": "320",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "2",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "170",
        "ok": "170",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "46",
        "ok": "46",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "41",
        "ok": "41",
        "ko": "-"
    },
    "percentiles1": {
        "total": "30",
        "ok": "30",
        "ko": "-"
    },
    "percentiles2": {
        "total": "54",
        "ok": "54",
        "ko": "-"
    },
    "percentiles3": {
        "total": "148",
        "ok": "148",
        "ko": "-"
    },
    "percentiles4": {
        "total": "165",
        "ok": "165",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 320,
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
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "16",
        "ok": "16",
        "ko": "-"
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
