const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const mode = process.env.NODE_ENV || 'development'; // 기본값을 development로 설정

console.log(mode);

module.exports = () => {
    let clientPath = path.resolve(__dirname, 'src/main/webapp/resources/js');
    let outputPath = path.resolve(__dirname, 'dist');

    return {
        mode: mode,
        entry: {
            index: clientPath + '/index.js'
        },
        output: {
            path: outputPath,
            filename: '[name].js'
        },
        devServer: {
            overlay: true,
            contentBase: outputPath,
            publicPath: '/',
            host: '0.0.0.0',
            port: 8081,
            proxy: {
                '**': 'http://127.0.0.1:8080'
            },
            stats: "errors-only",
            inline: true,
            hot: true
        },
        module: {
            rules: [
                {
                    test: /\.js$/,
                    loader: 'babel-loader',
                    exclude: /node_modules/,
                },
                {
                    test: /\.(css)$/,
                    use: [{
                        loader: MiniCssExtractPlugin.loader
                    }, {
                        loader: 'css-loader'
                    }]
                },
                {
                    test: /\.(jpe?g|png|gif)$/i,
                    loader: 'url-loader',
                    options: {
                        // publicPath: "./dist/",
                        name: '[name].[ext]?[hash]',
                        limit: 1024 * 20 // 10kb
                    },
                },
            ]
        },
        plugins: [
            // new CleanWebpackPlugin({}),
            new MiniCssExtractPlugin({
                filename: '[name].css',
            }),
            // ...(process.env.NODE_ENV === 'production'
            //     ? [
            //         new MiniCssExtractPlugin({
            //             filename: '[name].css',
            //         }),
            //     ]
            //     : []),
        ]
    }
}