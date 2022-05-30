import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'
import axios from 'axios'
import VueAxios from 'vue-axios'

import '@/assets/css/common.css'
import '@/assets/css/reset.css'
import '@/assets/font/icon/iconfont.css'
import 'element-plus/theme-chalk/el-message.css'
import 'element-plus/theme-chalk/el-message-box.css'
import 'default-passive-events'


//markdown编辑器
import VMdEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
//预览组件
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
// highlightjs
import hljs from 'highlight.js';
// Prism
import Prism from 'prismjs';
// highlight code
import 'prismjs/components/prism-json';

//热力图组件
import VueCalendarHeatmap from 'vue3-calendar-heatmap'

VMdPreview.use(githubTheme, {
    Hljs: hljs,
});

const app = createApp(App)
app
    .use(createPinia())
    .use(router)
    .use(VueAxios,axios)
    .use(VMdEditor)
    .use(VMdPreview)
    .use(VueCalendarHeatmap)
    .mount('#app')


VMdEditor.use(vuepressTheme, {
    Prism,
});
