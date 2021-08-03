<template>
  <div id="app">
    <div class="nav">
      <navigation 
      name="Fixer"
      :navLinks="[

        {
          name: 'Home',
          link: '/home',

        },

        {
          name: 'Create',
          link: '/home',
          dropdown: false,          
        },
          
        {

          name: 'Review',
          link: '/review',
          dropdown: false,
        },
        {
          name: 'Register',
          link: '/register',
          dropdown: false,
        },
        {
          name: 'Login',
          link: '/login',
          dropdown: false,
        },
        {
          name: 'Logout',
          link: '/logout',
          dropdown: false,
        }
          
       ]"
      />
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
import Navigation from "./components/Navigation.vue";
export default {
  components: {
    Navigation,
  }
  
}
</script>

