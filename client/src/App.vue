<template>
    <v-app id="inspire">
        <v-navigation-drawer
                v-model="drawer"
                :clipped="$vuetify.breakpoint.lgAndUp"
                app>
            <v-list dense>
                <template v-for="item in items">

                        <v-list-item :key="item.text" link :to="{path: item.path}">
                            <v-list-item-action>
                                <v-icon>{{ item.icon }}</v-icon>
                            </v-list-item-action>
                            <v-list-item-content>
                                <v-list-item-title>
                                    {{ item.text }}
                                </v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>

                </template>
            </v-list>
        </v-navigation-drawer>

        <v-app-bar
                :clipped-left="$vuetify.breakpoint.lgAndUp"
                app
                color="blue darken-3"
                dark>
            <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title
                    style="width: 300px"
                    class="ml-0 pl-4">
                <span class="hidden-sm-and-down">Diego Romero Test</span>
            </v-toolbar-title>
            <v-spacer></v-spacer>
        </v-app-bar>
        <v-content>
            <v-container
                    class="fill-height"
                    fluid
            >
                <v-row


                >
                    <router-view></router-view>

                </v-row>

            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    export default {
        props: {
            source: String,
        },
        data: () => ({
            dialog: false,
            drawer: null,
            items: [
                {icon: 'mdi-contacts', text: 'Users', path:'/user'}
            ],
        }),
    }
</script>