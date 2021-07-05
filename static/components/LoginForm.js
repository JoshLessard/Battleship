app.component('login-form', {
    template:
    /*html*/
    `
    <link rel="stylesheet" href="./components/login-form.css" />

    <form class="login-form" v-on:submit.prevent="onSubmit">
      <label for="name">Name:</label>
      <input id="name" v-model="name">
      
      <label for="email">Email:</label>
      <input id="email" v-model="email">

      <input
        class="button"
        type="submit"
        value="Log in"
        v-on:click.prevent="! allFieldsFilled"
        v-bind:class="{ disabledButton: ! allFieldsFilled }"
      >
    </form>
    `,
    data: function() {
      return {
        name: '',
        email: ''
      }
    },
    methods: {
      onSubmit: function() {
        let userProfile = {
          name: this.name,
          email: this.email
        }
        this.$emit( 'login-attempt', userProfile )
  
        this.name = ''
        this.email = ''
      }
    },
    computed: {
      allFieldsFilled: function() {
        return this.name !== '' && this.email !== '';
      }
    }
  })