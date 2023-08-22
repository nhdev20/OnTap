<template>
  <div id="register" class="text-center">
    <form id="register-form" @submit.prevent="register">
      <img id="ontap-logo" src="../assets/images/ontap-logo.png" alt="on-tap">
      <h1 id="main-header">Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username" class="labels">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password" class="labels">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword" class="labels">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <div id="button-div">
      <button type="submit">Create Account</button>
      </div>
      <p><router-link id="login-link" :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
#register{
  display: flex;
  justify-content: center;
  align-items: center;
  color: rgb(172, 13, 13);
}
#register-form {
  border: 1px solid rgb(172, 13, 13);
  border-radius: 10px;
  padding: 10px;
  margin: 10px;
  background: rgba(251,230,194);
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
#login-link {
  color: rgba(172, 13, 13, .7);
  font-size: .8rem;
}
button {
  font-size: 12px;
  background: rgba(251,230,194, .3);
  color: #721c24;
  border-color: rgb(244,139,41);
  height: 30px;
  width: 100px;
  
  border-radius: 10px;
  margin-top: 10px;
  text-align: center;
}
button:hover {
    cursor: pointer !important;
}
#button-div{
  margin-bottom: 10px;
  white-space: nowrap;
}
#ontap-logo {
  width: 60%;
  height: auto;
}
</style>
