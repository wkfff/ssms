var path = require('path');

module.exports = function (grunt) {
    grunt.initConfig({
        watch: {
            typescript: {
                files: ['main/webapp/typescript/**/*.ts'],
                tasks: ['ts', 'copy'],
                options: {
                    spawn: false
                }
            }
        },
        copy: {
            copyTSMap: {
                cwd: 'dist',
                src: '*.js.map',
                dest: 'main/webapp/typescript/maps/',
                filter: 'isFile',
                expand: true
            },
            copyJs: {
                cwd: 'dist',
                src: '*.js',
                dest: 'main/webapp/resource/js/',
                filter: 'isFile',
                expand: true
            }
        },
        ts: {
            build: {
                //When abc.ts is compiled to abc.js, it will reference /maps/abc.js.map
                src: ['main/webapp/typescript/**/*.ts', 'typings/tsd.d.ts'],
                outDir: 'dist',
                options: {
                    mapRoot: "/typescript/maps",
                    sourceRoot: "/typescript",
                    target: "es3", //for IE8 and below "es5" (default) | "es3" | "es6",
                    verbose: true, //false (default) | true
                    comments: true, //preserves comments in output. true | false (default)
                    declaration: true, // true | false (default)
                    module: "amd", // "amd" (default) | "commonjs" | ""
                    sourceMap: true, // true (default) | false
                    fast: 'never'
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-ts');

    grunt.event.on('watch', function (action, filepath) {
        var d = '!dist/'+path.basename(filepath, '.ts')+'.d.ts';
        grunt.config('ts.build.src', [filepath, 'typings/tsd.d.ts', 'dist/*.d.ts', d]);
    });

    grunt.registerTask('dev', ['ts', 'copy']);
    grunt.registerTask('default', ['watch']);
};
