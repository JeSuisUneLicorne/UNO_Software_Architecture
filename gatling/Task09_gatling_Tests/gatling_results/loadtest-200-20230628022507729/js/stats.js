var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "400",
        "ok": "326",
        "ko": "74"
    },
    "minResponseTime": {
        "total": "9",
        "ok": "9",
        "ko": "23"
    },
    "maxResponseTime": {
        "total": "211",
        "ok": "211",
        "ko": "97"
    },
    "meanResponseTime": {
        "total": "94",
        "ok": "97",
        "ko": "77"
    },
    "standardDeviation": {
        "total": "52",
        "ok": "56",
        "ko": "15"
    },
    "percentiles1": {
        "total": "96",
        "ok": "112",
        "ko": "79"
    },
    "percentiles2": {
        "total": "134",
        "ok": "143",
        "ko": "83"
    },
    "percentiles3": {
        "total": "176",
        "ok": "180",
        "ko": "96"
    },
    "percentiles4": {
        "total": "201",
        "ok": "203",
        "ko": "97"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 326,
    "percentage": 82
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
    "count": 74,
    "percentage": 19
},
    "meanNumberOfRequestsPerSecond": {
        "total": "200",
        "ok": "163",
        "ko": "37"
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
        "total": "200",
        "ok": "126",
        "ko": "74"
    },
    "minResponseTime": {
        "total": "23",
        "ok": "62",
        "ko": "23"
    },
    "maxResponseTime": {
        "total": "154",
        "ok": "154",
        "ko": "97"
    },
    "meanResponseTime": {
        "total": "110",
        "ok": "130",
        "ko": "77"
    },
    "standardDeviation": {
        "total": "32",
        "ok": "22",
        "ko": "15"
    },
    "percentiles1": {
        "total": "108",
        "ok": "133",
        "ko": "79"
    },
    "percentiles2": {
        "total": "143",
        "ok": "148",
        "ko": "83"
    },
    "percentiles3": {
        "total": "151",
        "ok": "152",
        "ko": "96"
    },
    "percentiles4": {
        "total": "153",
        "ok": "153",
        "ko": "97"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 126,
    "percentage": 63
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
    "count": 74,
    "percentage": 37
},
    "meanNumberOfRequestsPerSecond": {
        "total": "100",
        "ok": "63",
        "ko": "37"
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
        "total": "200",
        "ok": "200",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "9",
        "ok": "9",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "211",
        "ok": "211",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "77",
        "ok": "77",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "61",
        "ok": "61",
        "ko": "-"
    },
    "percentiles1": {
        "total": "58",
        "ok": "58",
        "ko": "-"
    },
    "percentiles2": {
        "total": "131",
        "ok": "131",
        "ko": "-"
    },
    "percentiles3": {
        "total": "187",
        "ok": "187",
        "ko": "-"
    },
    "percentiles4": {
        "total": "204",
        "ok": "204",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 200,
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
        "total": "100",
        "ok": "100",
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
