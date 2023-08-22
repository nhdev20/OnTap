<template>
  <div id="app">

    <app-header v-show="!hideHeader" />
    
    <router-view />

  </div>
</template>

<script>
import AppHeader from './components/AppHeader.vue'

export default {
  components: {
    AppHeader,
    
  },
  computed: {
    hideHeader() {
      return this.$route.name === 'login' || this.$route.name === 'register' || this.$route.name === 'account-brewer' || this.$route.name === 'account-user' || this.$route.name === 'beer-details' || this.$route.name == 'brewer-beer-details';
    }
  }
}
</script>

<style>
  @import url('https://fonts.googleapis.com/css2?family=Alata&family=Alfa+Slab+One&family=Anton&family=Passion+One&display=swap');
  * {
    font-family: 'Alata', sans-serif;
  }
</style>