<template>
    <v-container>
        <v-row>
            <v-overlay :value="loading">
                <v-progress-circular indeterminate size="64"></v-progress-circular>
            </v-overlay>
            <v-alert type="success" dismissible v-if="!!message">
                {{message}}
            </v-alert>
            <v-spacer></v-spacer>
            <v-card>
                <v-card-title>
                    <v-text-field
                            v-model="search"
                            append-icon="mdi-magnify"
                            @keyup.enter="fetchData()"
                            label="Search by name"
                            single-line
                            hide-details
                    ></v-text-field>
                </v-card-title>
                <v-data-table
                        :headers="headers"
                        :items="users"
                        :options.sync="options"
                        :server-items-length="totalUsers"
                        :loading="loading"
                        :search="search"
                        class="elevation-1">

                    <template v-slot:item.actions="{ item }">
                        <v-icon
                                small
                                class="mr-2"
                                @click="editItem(item)"
                        >
                            mdi-pencil
                        </v-icon>
                        <v-icon
                                small
                                @click="confirmDelete(item)"
                        >
                            mdi-delete
                        </v-icon>

                        <router-link :to="{ name: 'task', params: {userId: item.id } }">
                            <v-icon small
                                    @click="confirmDelete(item)">
                                mdi-calendar-text
                            </v-icon>
                        </router-link>
                    </template>
                </v-data-table>
            </v-card>
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
                <create-edit-user @createUser="createUser" @updateUser="updateUser" @close="dialog = false" :user="user" :isEdit="isEdit"></create-edit-user>
            </v-dialog>

            <confirm ref="confirm"></confirm>

        </v-row>
    </v-container>
</template>
<script>
    import CreateEditUser from './user/CreateEditUser.vue'
    import Confirm from './common/Dialog.vue'

    export default {
        components: {
            CreateEditUser,
            Confirm
        },
        watch: {
            options: {

                handler () {
                    this.fetchData()
                },
                deep: true,
            },
        },
        methods: {
            editItem(user){
                this.isEdit = true
                this.user = Object.assign({}, user)
                this.dialog = true
            },
            confirmDelete(user){
                this.$refs.confirm.open('Delete', 'Are you sure?', { color: 'red' }).then((confirm) => {
                    if(confirm){
                        this.dropUser(user.id)
                    }
                })
            },
            fetchData() {
                this.loading = true
                let params = {
                    itemsPerPage: this.options.itemsPerPage,
                    pageNo: this.options.page - 1,
                    sortBy: this.options.sortBy.toString(),
                    desc: this.options.sortDesc.toString(),
                    searchName: this.search
                }

                this.$http.get('/user', {params: params}, )
                    .then((response) => {
                        this.users = response.data.content
                        this.totalUsers = response.data.totalElements
                        this.loading = false
                    })
                    .catch((error) => console.log(error))
            },
            dropUser(id){
                this.loading = true
                this.$http.delete('/user/' + id )
                    .then(() => {
                        this.fetchData()
                        this.loading = false
                        this.successMessage()
                    })
                    .catch((error) => console.log(error))
            },
            successMessage(){
                this.message = "All changes are saved"
            },
            updateUser(userEdited){
                this.loading = true
                this.$http.put('/user/'+ userEdited.id,  userEdited )
                    .then(() => {
                        this.fetchData()
                        this.loading = false
                        this.dialog = false
                    })
                    .catch((error) => console.log(error))
            },
            createUser(newUser){
                console.log(newUser)
                this.loading = true
                this.$http.post('/user', newUser )
                    .then(() => {
                        this.fetchData()
                        this.loading = false
                        this.dialog = false
                    })
                    .catch((error) => console.log(error))
            },
            onCreate(){
                this.user = new Object()
                this.isEdit = false
                this.dialog = true
            }
        },
        data () {
            return {
                loading: true,
                fullPage: true,
                options: {},
                dialog: false,
                confirm: false,
                message: null,
                search: '',
                isEdit: false,
                headers: [
                    {   text: 'Id', align: 'start', value: 'id'},
                    {   text: 'Name', value: 'name' },
                    { text: 'Actions', value: 'actions', sortable: false }
                ],
                users: [],
                totalUsers: 0,
                user: null
            }
        },
    }
</script>