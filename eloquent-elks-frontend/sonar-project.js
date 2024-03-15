const sonarScanner = require("sonarqube-scanner");

sonarScanner(
    {
        serverUrl: "https://sonarcloud.io",
        token: process.env.SONAR_TOKEN,
        options:{
            "sonar.projectKey": "eloquent-elks_EloquentElks_Frontend",
			"sonar.projectName": "eloquent-elks-frontend",
            "sonar.organization": "eloquent-elks",
            "sonar.sources": "src",
            "sonar.tests": "src",
            "sonar.test.inclusions": "src/*.test.js,src/**/*.test.js",
            "sonar.typescript.lcov.reportPaths": "coverage/lcov.info"
        }
    }
)