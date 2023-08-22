<template>
  <div id="login">
    
    <form id="login-form" @submit.prevent="login">
      <img id="ontap-logo" src="../assets/images/ontap-logo.png" alt="on-tap">
      <h1 id="main-header">Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username" class="labels">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password" class="labels">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div id ="button-div" class="form-input-group">
      <button type="submit">Sign in</button>
      </div>
      <p>
      <router-link id="register-link" :to="{ name: 'register' }">Need an account? Sign up!</router-link>
      </p>
    </form>
    
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}

#login{
  display: flex;
  justify-content: center;
  align-items: center;
  color: rgb(172, 13, 13);
}
#login-form {
  border: 1px solid rgb(172, 13, 13);
  border-radius: 10px;
  padding: 10px;
  margin: 10px;
  background: rgba(251,230,194);
}
.centered-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  padding: 1rem;
  flex-direction: column; 
  /* items vertical */
}
#main-header {
  margin-top: 0;
  margin-bottom: 30px;
}
form {
  width: 100%; 
  max-width: 400px; /* This will prevent the form from filling the whole page width */
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-input-group {
  margin-bottom: 1rem;
  width: 100%;
  text-align: center;
}

label {
  margin-bottom: 0.5rem;
  display: block; /* Making labels block-level so they stack above the inputs */
}
.labels {
  text-align: center;
}
input[type="text"],
input[type="password"] {
  width: 80%; /* This ensures the inputs fill their container but not more */
  justify-content: center;
}

#register-link {
  color: rgba(172, 13, 13, .7);
  font-size: .8rem;
}
button {
  background: rgba(251,230,194, .3);
  color: #721c24;
  border-color: rgb(244,139,41);
  height: 35px;
  width: 60px;
  
  border-radius: 10px;
  margin-top: 10px;
  text-align: center;
}
button:hover {
    cursor: pointer !important;
}
#button-div{
  margin-bottom: 10px;
}
#ontap-logo {
  width: 60%;
  height: auto;
}

.alert {
  padding: 0.5rem;
  border-radius: 4px;
  margin-bottom: 1rem;
  width: 100%;
  text-align: center; /* Centering the alert text */
}

.alert-error {
  background-color: #f8d7da;
  color: #721c24;
  border: 1px solid #f5c6cb;
}

.alert-success {
  background-color: #d4edda;
  color: #155724;
  border: 1px solid #c3e6cb;
}

</style>