<template>
    <div>
        <v-card>
            <v-card-title v-if="isEdit" class="grey darken-2">
                Edit User
            </v-card-title>
            <v-card-title v-else class="grey darken-2">
                Create User
            </v-card-title>
            <v-container>
                <v-row class="mx-2">

                    <v-col cols="3">
                        <v-text-field v-if="isEdit"
                                prepend-icon="mdi-account-card-details-outline"
                                placeholder="Id"
                                v-model="user.id"
                                disabled
                        ></v-text-field>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field
                                placeholder="Name"
                                v-model="user.name"
                        ></v-text-field>
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
            user: Object,
            isEdit: Boolean
        },
        methods: {
            save(){
                if(!!this.user && !!this.user.id){
                    this.updateUser()
                } else {
                    this.createUser()
                }
            },
            updateUser(){
                this.$emit('updateUser', this.user)
            },
            createUser(){
                this.$emit('createUser', this.user)
            }
        }
    }

</script>