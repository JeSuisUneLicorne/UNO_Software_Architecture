var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "6000",
        "ok": "3930",
        "ko": "2070"
    },
    "minResponseTime": {
        "total": "4",
        "ok": "10",
        "ko": "4"
    },
    "maxResponseTime": {
        "total": "3469",
        "ok": "3469",
        "ko": "3314"
    },
    "meanResponseTime": {
        "total": "332",
        "ok": "325",
        "ko": "345"
    },
    "standardDeviation": {
        "total": "575",
        "ok": "595",
        "ko": "536"
    },
    "percentiles1": {
        "total": "69",
        "ok": "65",
        "ko": "89"
    },
    "percentiles2": {
        "total": "316",
        "ok": "160",
        "ko": "457"
    },
    "percentiles3": {
        "total": "1693",
        "ok": "1756",
        "ko": "1512"
    },
    "percentiles4": {
        "total": "2674",
        "ok": "2723",
        "ko": "2476"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 3374,
    "percentage": 56
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 223,
    "percentage": 4
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 333,
    "percentage": 6
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 2070,
    "percentage": 35
},
    "meanNumberOfRequestsPerSecond": {
        "total": "11.742",
        "ok": "7.691",
        "ko": "4.051"
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
        "total": "3000",
        "ok": "3000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "10",
        "ok": "10",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "931",
        "ok": "931",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "62",
        "ok": "62",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "49",
        "ok": "49",
        "ko": "-"
    },
    "percentiles1": {
        "total": "53",
        "ok": "53",
        "ko": "-"
    },
    "percentiles2": {
        "total": "77",
        "ok": "77",
        "ko": "-"
    },
    "percentiles3": {
        "total": "130",
        "ok": "130",
        "ko": "-"
    },
    "percentiles4": {
        "total": "205",
        "ok": "205",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 2998,
    "percentage": 100
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 2,
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
        "total": "5.871",
        "ok": "5.871",
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
        "total": "3000",
        "ok": "930",
        "ko": "2070"
    },
    "minResponseTime": {
        "total": "4",
        "ok": "82",
        "ko": "4"
    },
    "maxResponseTime": {
        "total": "3469",
        "ok": "3469",
        "ko": "3314"
    },
    "meanResponseTime": {
        "total": "601",
        "ok": "1170",
        "ko": "345"
    },
    "standardDeviation": {
        "total": "717",
        "ok": "743",
        "ko": "536"
    },
    "percentiles1": {
        "total": "305",
        "ok": "924",
        "ko": "89"
    },
    "percentiles2": {
        "total": "877",
        "ok": "1578",
        "ko": "457"
    },
    "percentiles3": {
        "total": "2268",
        "ok": "2683",
        "ko": "1512"
    },
    "percentiles4": {
        "total": "2891",
        "ok": "3142",
        "ko": "2476"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 376,
    "percentage": 13
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 221,
    "percentage": 7
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 333,
    "percentage": 11
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 2070,
    "percentage": 69
},
    "meanNumberOfRequestsPerSecond": {
        "total": "5.871",
        "ok": "1.82",
        "ko": "4.051"
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
