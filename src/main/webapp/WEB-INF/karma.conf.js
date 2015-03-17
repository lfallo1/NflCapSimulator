// Karma configuration
// Generated on Mon Mar 16 2015 19:32:04 GMT-0400 (Eastern Daylight Time)

module.exports = function(config) {
  config.set({

    // base path that will be used to resolve all patterns (eg. files, exclude)
    basePath: '',


    // frameworks to use
    // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
    frameworks: ['jasmine'],


    // list of files / patterns to load in the browser
    files: [
            'bower_components/jquery/dist/jquery.min.js',
            'bower_components/angular/angular.min.js',
            'bower_components/angular-route/angular-route.min.js',
            'bower_components/angular-resource/angular-resource.min.js',
            'bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js',
            'bower_components/angular-mocks/angular-mocks.js',
            'app/js/controllers.js',
            'app/js/app.js',
            'app/js/services/**/*.js',
            'app/js/directives/**/*.js',
            'app/js/filters/**/*.js',
            'app/js/controllers/**/*.js',
            'test/spec/**/*.js'
    ],


    // list of files to exclude
    exclude: [
    ],


    preprocessors: {
        'app/js/**/*.js': 'coverage'
    },


    // test results reporter to use
    // possible values: 'dots', 'progress'
    // available reporters: https://npmjs.org/browse/keyword/karma-reporter
    reporters: ['progress', 'html', 'coverage'],

    coverageReporter: {
        type: 'html',
        dir: 'test/results/coverage/'
    },

    htmlReporter: {
        outputDir: 'test/results/html/', // where to put the reports
        templatePath: null, // set if you moved jasmine_template.html
        focusOnFailures: true, // reports show failures on start
        namedFiles: false, // name files instead of creating sub-directories
        pageTitle: null, // page title for reports; browser info by default
        urlFriendlyName: false, // simply replaces spaces with _ for files/dirs
        // experimental
        preserveDescribeNesting: false, // folded suites stay folded
        foldAll: false // reports start folded (only with preserveDescribeNesting)
    },


    // web server port
    port: 9876,


    // enable / disable colors in the output (reporters and logs)
    colors: true,


    // level of logging
    // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
    logLevel: config.LOG_INFO,


    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,


    // start these browsers
    // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
    //1. Chrome
    //2. PhantomJS
    browsers: ['Chrome'],


    // Continuous Integration mode
    // if true, Karma captures browsers, runs the tests and exits
    singleRun: false
  });
};
