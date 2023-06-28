var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "640",
        "ok": "414",
        "ko": "226"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "53",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "18784",
        "ok": "18784",
        "ko": "18254"
    },
    "meanResponseTime": {
        "total": "6767",
        "ok": "10263",
        "ko": "362"
    },
    "standardDeviation": {
        "total": "8164",
        "ok": "8120",
        "ko": "2131"
    },
    "percentiles1": {
        "total": "514",
        "ok": "16054",
        "ko": "25"
    },
    "percentiles2": {
        "total": "16356",
        "ok": "16753",
        "ko": "69"
    },
    "percentiles3": {
        "total": "18343",
        "ok": "18507",
        "ko": "239"
    },
    "percentiles4": {
        "total": "18702",
        "ok": "18741",
        "ko": "15448"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 139,
    "percentage": 22
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 29,
    "percentage": 5
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 246,
    "percentage": 38
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 226,
    "percentage": 35
},
    "meanNumberOfRequestsPerSecond": {
        "total": "20.645",
        "ok": "13.355",
        "ko": "7.29"
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
        "total": "320",
        "ok": "159",
        "ko": "161"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "53",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "946",
        "ok": "946",
        "ko": "312"
    },
    "meanResponseTime": {
        "total": "286",
        "ok": "502",
        "ko": "73"
    },
    "standardDeviation": {
        "total": "273",
        "ok": "223",
        "ko": "86"
    },
    "percentiles1": {
        "total": "235",
        "ok": "465",
        "ko": "28"
    },
    "percentiles2": {
        "total": "462",
        "ok": "625",
        "ko": "70"
    },
    "percentiles3": {
        "total": "876",
        "ok": "912",
        "ko": "238"
    },
    "percentiles4": {
        "total": "931",
        "ok": "939",
        "ko": "295"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 130,
    "percentage": 41
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 29,
    "percentage": 9
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
    "count": 161,
    "percentage": 50
},
    "meanNumberOfRequestsPerSecond": {
        "total": "10.323",
        "ok": "5.129",
        "ko": "5.194"
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
        "total": "320",
        "ok": "255",
        "ko": "65"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "159",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "18784",
        "ok": "18784",
        "ko": "18254"
    },
    "meanResponseTime": {
        "total": "13247",
        "ok": "16350",
        "ko": "1076"
    },
    "standardDeviation": {
        "total": "7016",
        "ok": "3248",
        "ko": "3880"
    },
    "percentiles1": {
        "total": "16357",
        "ok": "16462",
        "ko": "21"
    },
    "percentiles2": {
        "total": "17624",
        "ok": "17839",
        "ko": "30"
    },
    "percentiles3": {
        "total": "18584",
        "ok": "18625",
        "ko": "10729"
    },
    "percentiles4": {
        "total": "18748",
        "ok": "18751",
        "ko": "17163"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 9,
    "percentage": 3
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
    "count": 246,
    "percentage": 77
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 65,
    "percentage": 20
},
    "meanNumberOfRequestsPerSecond": {
        "total": "10.323",
        "ok": "8.226",
        "ko": "2.097"
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
