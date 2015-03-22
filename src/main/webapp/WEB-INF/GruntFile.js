module.exports = function(grunt) {
	// Load the plugin that provides the "jshint" task.
	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-karma');
	// Project configuration.
	grunt.initConfig({
		clean: {
			tests : {
				src : ["test/results/coverage/**/*", "test/results/html/**/*"] 
			}
		},
		jshint : {
			options : {
				curly : true,
				eqeqeq : true
			},
			target1 : [ 'Gruntfile.js', 'app/js/**/*.js' ]
		},
		copy : {
			css : {
				expand : true,
				cwd : 'app/css',
				src : '*.css',
				dest : 'build/css'		
			},
			images : {
				expand : true,
				cwd : 'app/images',
				src : '**/*.*',
				dest : 'build/images'				
			},
			views : {
				expand : true,
				cwd : 'app/views',
				src : '*.jsp',
				dest : 'build/views'				
			}
		},		
		uglify : {
			my_target : {
				files : [ {
					expand : true,
					cwd : 'app/js',
					src : '**/*.js',
					dest : 'build/js'
				} ]
			}
		},
        karma : {
            unit: {
                configFile: 'karma.conf.js',
                singleRun: false
            }
        }
	});

	// Define the default task
	grunt.registerTask('default', [ 'jshint' ]);

	grunt.registerTask('build', ['uglify:my_target', 'copy']);
	
	grunt.registerTask('test', ['clean:tests', 'karma:unit']);
	
};