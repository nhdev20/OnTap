<template>
  <div class="app-header">
    <div id="main-header">
      <div id="logo-div">
      <router-link :to="{ name: 'home' }">
        <img @click="defaultBrewerPageView"
          class="nav-link"
          id="ontap-logo"
          src="../../assets/images/ontap-logo.png"
          alt="on-tap"
      /></router-link>
      </div>

      <div id="brewery-name">
          <p>{{brewery.name}}</p>
          </div>

      <div class="account-logout">
        <div id="logout">
          <button class="logout-btn" @click="$router.push({name: 'logout'})" v-if="$store.state.token != ''">Log Out</button>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    props: ["brewery"],
    methods: {
        defaultBrewerPageView() {
            this.$store.commit('CHANGE_BREWER_PAGE_VIEW', 'brewery_beer_list');
        }
    }
};
</script>

<style scoped>
.app-header {
    background: rgba(251,230,194);
    border-top: 1px solid rgb(172, 13, 13);
    border-radius: 10px 10px 0 0;
    padding-top: 20px;
    padding-bottom: 20px;
}
#main-header {
    color: rgb(172, 13, 13);
    display: grid;
    grid-template-columns: 1fr 2fr 1fr;
    grid-template-areas: 
    "logo name logout";
    align-items: center;
    justify-items: center;
}
#logo-div{
    grid-area: logo;
}
#beer-name{
    grid-area: name;
    text-align: center;
}
#account-logout{
    grid-area: logout;
}
#logout{
    display: flex;
    justify-content: flex-end;
}

.logout-btn{
    /* margin-right: 60px; */
    background-color: white;
    color: rgb(172, 13, 13);
    font-size: 1rem;
    height: 40px;
    width: 160px;
    border-radius: 10px;
    border: 2px solid rgb(172, 13, 13)
}
.logout-btn:hover {
    cursor: pointer !important;
}
#ontap-logo {
   width: 180px;
   height: auto;
   
}
p{
    font-size: 3.5rem;
}
</style>