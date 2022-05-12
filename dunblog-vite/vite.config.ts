import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import * as path from "path";
import {fileURLToPath, URL} from "url";
import {ElementPlusResolver, VantResolver} from "unplugin-vue-components/resolvers";
const setAlias = (alias: [string, string][]) => alias.map(v => {return { find: v[0], replacement: path.resolve(__dirname, v[1]) }})
// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    }
  },
  plugins: [
      vue(),
      AutoImport({
          imports: ["vue", "vue-router"], // 自动导入vue和vue-router相关函数
      }),
      Components({
        resolvers: [ElementPlusResolver(),VantResolver()],
      }),
  ]
})
