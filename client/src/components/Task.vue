<template>
    <v-container>
        <v-overlay :value="loading">
            <v-progress-circular indeterminate size="64"></v-progress-circular>
        </v-overlay>
        <h1 v-if="!!user">{{user.name}} Task's</h1>
        <v-spacer></v-spacer>
        <v-row>

                <v-card style="width: 200px" v-for="task in tasks" :key="task.id" :color="task.taskState == 'TO_DO' ? 'rgb(179, 69, 69)' : '#385F73'" >
                    <v-card-title class="headline">{{task.description}}</v-card-title>
                    <v-card-subtitle>{{task.description}}.</v-card-subtitle>

                    <v-card-actions>
                        <v-btn text>Listen Now</v-btn>
                    </v-card-actions>
                </v-card>


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
            <create-edit-task @createUser="createTask" @updateUser="updateTask"
                              @close="dialog = false" :task="task" :states="states"
                              :isEdit="isEdit" :selectedState="selectedState"></create-edit-task>
        </v-dialog>
    </v-container>
</template>
<script>
    import CreateEditTask from '../components/user/CreateEditTask.vue'
    //import Confirm from './common/Dialog.vue'

    export default {
        components: {
            CreateEditTask,
            //Confirm
        },
        props: {
            userId: String
        },

        data () {
            return {
                loading: true,
                user: null,
                tasks: [],
                task: null,
                dialog: false,
                isEdit: false,
                states: [{code: 'TO_DO', text: 'This task is to do'},{code: 'DONE', text: 'this task is done'}],
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
            createTask(){
                console.log('createTask')
            }

        },
        mounted(){
            this.getUser()
        }
    }
</script>