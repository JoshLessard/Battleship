const app = Vue.createApp({
    data: function() {
        return {
            name: '',
            email: ''
        }
    },
    methods: {
        loginAttempt: function( userProfile ) {
            this.name = userProfile.name
            this.email = userProfile.email
        }
    },
    computed: {
        loggedIn: function() {
            return this.name !== '' && this.email !== ''
        }
    }
})