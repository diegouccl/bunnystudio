import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import VueRouter from 'vue-router'
import routes from './routes/routes'
import axios from 'axios'
import vueAxios from 'vue-axios'


Vue.config.productionTip = false
Vue.use(VueRouter)

//config axios
axios.defaults.baseURL = 'http://localhost:8080/'
Vue.prototype.$http = axios

Vue.use(vueAxios, axios)

// configure router
const router = new VueRouter({
    routes, // short for routes: routes
    linkActiveClass: 'active'
})

new Vue({
    vuetify,
    render: h => h(App),
    router
}).$mount('#app')
