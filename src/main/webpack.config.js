const path = require('path');
const TarserPlugin = require('terser-webpack-plugin');

module.exports = {
    entry: {
        main: ['@babel/polyfill', './webapp/resources/js/main/main.js'],
        test: ['@babel/polyfill', './webapp/resources/js/test/test.js']
    },
    output: {
        path: path.resolve(__dirname, './webapp/resources/ejs'),
        filename: '[name].js',
        libraryTarget: "umd", // output.libraryTarget을 umd로 설정하면 모듈은 <script src=""> 로드 뿐만 아니라 모든 방식의 로더에서 사용할 수 있음
    },
    optimization: {
        minimizer:
            [
                new TarserPlugin({
                    terserOptions: {
                        compress: {
                            drop_console: true,
                        },
                    },
                }),
            ]
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env'],
                        plugins: ["@babel/plugin-proposal-export-default-from"]
                    }
                }
            }
        ]
    },
    devtool: 'source-map',
    mode: 'development'
};