const sonarScanner = require('sonarqube-scanner');

sonarScanner({
  serverUrl: 'https://sonarcloud.io',
  token: process.env.SONAR_TOKEN,
  options: {
    'sonar.projectKey': 'eloquent-elks_EloquentElks_poi_density_api',
	  'sonar.projectName': 'eloquent-elks-poi-density-api',
    'sonar.organization': 'eloquent-elks',
    'sonar.sources': 'server',
    'sonar.exclusions': 'server/common/**',
    'sonar.tests': 'test',
    'sonar.test.inclusions': 'test/*.test.js,test/**/*.test.js',
    'sonar.typescript.lcov.reportPaths': 'coverage/lcov.info',
  },
});
