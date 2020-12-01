module.exports = {
    outputDir: "../server/web",
    publicPath: "/myblog",
    devServer: {
        port: 8081,
        proxy: {
            // CORS 问题
            // axios(http://localhost:9999/myblog/comment/list)
            // axios(/api/comment/list)
            "/api": {
                target: "http://localhost:9999",
                pathRewrite: {
                    "/api": "/myblog"
                },
                changeOrigin: true
            }
        }
    }
}