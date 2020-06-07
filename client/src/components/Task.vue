<template>
    <v-container>
        <v-overlay :value="loading">
            <v-progress-circular indeterminate size="64"></v-progress-circular>
        </v-overlay>
        <h1 v-if="!!user && tasks.length > 0 ">{{user.name}} Task's</h1>
        <v-spacer></v-spacer>
        <h1 v-if="!!user && tasks.length <= 0 ">No task to show for {{user.name}}</h1>
        <v-row class="col-12">

                <v-card class="col-3" style="margin: 1em;" v-for="task in tasks" :key="task.id" :color="task.taskState == 'TO_DO' ? 'rgb(179, 69, 69)' : 'rgb(59, 132, 65)'" >
                    <v-card-title class="headline">{{task.description}}</v-card-title>
                    <v-card-subtitle>{{task.description}}.</v-card-subtitle>
                    {{task.taskState.description}}

                    <v-card-actions class="float-right">
                        <v-btn @click="confirmDelete(task)">
                            <v-icon
                                small>
                            mdi-delete
                        </v-icon></v-btn>
                        <v-btn @click="editItem(task)">
                            <v-icon
                                    small
                                    class="mr-2"
                            >
                                mdi-pencil
                            </v-icon>
                        </v-btn>

                    </v-card-actions>
                </v-card>
            <v-spacer></v-spacer>
        </v-row>
        <v-btn bottom
               color="pink"
               dark
               fab
               fixed
               right
               @click="onCreate">
            <v-icon>mdi-plus</v-icon>

        </v-btn>
        <v-dialog v-model="dialog"
                  width="800px">
            <create-edit-task @createTask="createTask" @updateTask="updateTask"
                              @close="dialog = false" :task="task" :states="states"
                              :isEdit="isEdit" :selectedState="selectedState"></create-edit-task>
        </v-dialog>
        <confirm ref="confirm"></confirm>
    </v-container>
</template>
<script>
    import CreateEditTask from '../components/user/CreateEditTask.vue'
    import Confirm from './common/Dialog.vue'

    export default {
        components: {
            CreateEditTask,
            Confirm
        },
        props: {
            userId: [String, Number]
        },

        data () {
            return {
                loading: true,
                user: null,
                tasks: [],
                task: null,
                dialog: false,
                isEdit: false,
                states: [{code: 'TO_DO', description: 'This task is to do'},{code: 'DONE', description: 'this task is done'}],
                selectedState: null
            }
        },
        methods: {
            fetchData() {
                this.loading = true
                let url = '/user/' + this.user.id + '/task'
                this.$http.get(url)
                    .then((response) => {
                        this.tasks = response.data
                        this.loading = false
                    })
                    .catch((error) => console.log(error))
            },
            getUser() {

                this.loading = true
                this.$http.get('/user/'+ this.userId )
                    .then((response) => {
                        this.user = response.data
                        this.fetchData()
                        this.loading = false
                    })
                    .catch((error) => console.log(error))
            },
            onCreate(){
                this.task = new Object()
                this.isEdit = false
                this.dialog = true
            },
            updateTask(){
                console.log('updateTask')
            },
            createTask(task){
                this.loading = true
                this.$http.post('/user/'+ this.user.id + '/task', task )
                    .then(() => {
                        this.fetchData()
                        this.loading = false
                        this.dialog = false
                    })
                    .catch((error) => console.log(error))
            },
            confirmDelete(task){
                this.$refs.confirm.open('Delete', 'Are you sure?', { color: 'red' }).then((confirm) => {
                    if(confirm){
                        this.dropTask(task.id)
                    }
                })
            },
            dropTask(id){
                this.loading = true
                this.$http.delete('/task/' + id )
                    .then(() => {
                        this.fetchData()
                        this.loading = false
                    })
                    .catch((error) => console.log(error))
            },

        },
        mounted(){
            this.getUser()
        }
    }
</script>