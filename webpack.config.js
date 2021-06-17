const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const mode = process.env.NODE_ENV || 'development'; // 기본값을 development로 설정

console.log(mode);

module.exports = () => {
    let clientPath = path.resolve(__dirname, 'src/main/webapp/client/js');
    let outputPath = path.resolve(__dirname, mode === 'development' ? 'out' : 'src/main/webapp/resources/js');

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
            contentBase: outputPath,
            publicPath: '/',
            host: '0.0.0.0',
            port: 8081,
            proxy: {
                '**': 'http://127.0.0.1:8080'
            },
            inline: true,
            hot: false
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
                    use: [
                        process.env.NODE_ENV === "production"
                            ? MiniCssExtractPlugin.loader
                            : "style-loader",
                        "css-loader",
                    ],
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
            // new MiniCssExtractPlugin({
            //     filename: outputPath +'/[name].css',
            // }),
            ...(process.env.NODE_ENV === 'production'
                ? [
                    new MiniCssExtractPlugin({
                        filename: 'src/main/webapp/resources/js/[name].css',
                    }),
                ]
                : []),
        ]
    }
}
