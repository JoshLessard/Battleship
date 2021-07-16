const app = Vue.createApp({
    data: function() {
        return {
            userId: null,
            stagingGame: null
        }
    },
    methods: {
        onLogin: function( submitEvent ) {
            this.userId = submitEvent.target.elements.userId.value
            submitEvent.target.elements.userId.value = null
        },
        onNewGame: function() {
            const requestOptions = {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify( { userId: this.userId } )
            }
            fetch( 'http://localhost:8080/stagingGame' )
                .then( response => response.json() )
                .then( data => alert( data ) )
        }
    },
    computed: {
        loggedIn: function() {
            return this.userId !== null
        }
    }
})