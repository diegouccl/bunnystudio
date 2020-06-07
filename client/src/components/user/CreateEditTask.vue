<template>
    <div>
        <v-card>
            <v-card-title v-if="isEdit" class="grey darken-2">
                Edit task
            </v-card-title>
            <v-card-title v-else class="grey darken-2">
                Create task
            </v-card-title>
            <v-container>
                <v-row class="mx-2">

                    <v-col cols="2">
                        <v-text-field v-if="isEdit"
                                      prepend-icon="mdi-account-card-details-outline"
                                      placeholder="Id"
                                      v-model="task.id"
                                      disabled
                        ></v-text-field>
                    </v-col>
                    <v-col cols="5">
                        <v-text-field
                                placeholder="Description"
                                v-model="task.description"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="5">

                        <v-select
                                v-model="taskState"
                                :items="states"
                                selected
                                label="Select Item"
                        ></v-select>
                    </v-col>



                </v-row>
            </v-container>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                        text
                        color="primary"
                        @click="$emit('close')"
                >Cancel
                </v-btn>
                <v-btn
                        text
                        @click="save()"
                >Save
                </v-btn>
            </v-card-actions>
        </v-card>
    </div>
</template>

<script>


    export default {
        props: {
            task: Object,
            isEdit: Boolean,
            states: Array
        },
        methods: {
            save(){

                if(!!this.task && !!this.task.id){
                    this.updateTask()
                } else {
                    this.createTask()
                }
            },
            updateTask(){
                this.$emit('updateTask', this.task)
            },
            createTask(){
                this.$emit('createTask', this.task)
            }
        },
        data () {
            return {
                dropdown_icon: [
                    { text: 'list', callback: () => console.log('list') },
                    { text: 'favorite', callback: () => console.log('favorite') },
                    { text: 'delete', callback: () => console.log('delete') },
                ],
                taskState: null
            }
        },
    }

</script>