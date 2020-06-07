import User from '../components/User.vue'
import Task from '../components/Task.vue'
import NotFound from '../components/NotFound.vue'
import App from '../App.vue'


const routes = [
    { path: '/', component: App,
        redirect: 'user',
        children: [
            {
                name: 'task',
                path: ':userId/task',
                component: Task,
                props: true
            },
            {
                path: 'user',
                component: User,
            }

        ]
    },
    {path: '*', component: NotFound}
]

export default routes