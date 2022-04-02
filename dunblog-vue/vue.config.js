
// module.exports = ({
//     // 选项
//
//     configureWebpack: {
//         resolve: {
//             extensions: ['.js', '.json', '.vue', '.scss', '.css'],
//             alias: {},
//         },
//         externals: {
//             'vue': 'Vue',
//             'vuex': 'Vuex',
//             'vue-router': 'VueRouter',
//             'element-plus': 'ElementPlus',
//         }
//     }
// })
const path = require('path');

const webpack = require('webpack')
const CompressionWebpackPlugin = require('compression-webpack-plugin')
const productionGzipExtensions = ['js', 'css']
const isProduction = process.env.NODE_ENV === 'production'



module.exports = {
    publicPath: '/',
    productionSourceMap: false,
    configureWebpack: {
        resolve: {
            extensions: ['.js', '.json', '.vue', '.scss', '.css'],
            alias: {
                '@': path.resolve(__dirname, './src'),
                '@i': path.resolve(__dirname, './src/assets'),
            }
        },
        plugins: [
            // Ignore all locale files of moment.js
            new webpack.IgnorePlugin(/^\.\/locale$/, /moment$/),

            // 配置compression-webpack-plugin压缩
            new CompressionWebpackPlugin({
                algorithm: 'gzip',
                test: new RegExp('\\.(' + productionGzipExtensions.join('|') + ')$'),
                threshold: 10240,
                minRatio: 0.8
            }),
            new webpack.optimize.LimitChunkCountPlugin({
                maxChunks: 5,
                minChunkSize: 100
            })
        ],
        externals: {
            // 'vue': 'Vue',
            // 'vuex': 'Vuex',
            // 'vue-router': 'VueRouter',
            // 'element-plus': 'ElementPlus',
        }

    },
}

