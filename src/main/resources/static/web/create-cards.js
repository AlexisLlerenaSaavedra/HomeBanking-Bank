const app=Vue.createApp({
    data(){
        return{

            checkedType:"CREDITO",
            checkedColor:"SILVER"

            
            
            
            
        }
    },
    methods:{
        crearCard(){

            axios.post("http://localhost:8080/api/clients/current/cards",`color=${this.checkedColor}&type=${this.checkedType}`)
            .then(response => window.location.href="http://localhost:8080/web/cards.html")
            .catch(err=>console.log("horrooor"))
           
        },
        cerrarSesion(){
            axios.post('/api/logout').then(response =>window.location.href = 'http://localhost:8080/web/index.html' )
            
    
        }

        
   
    

    
    
    
    },
    created(){
        
        
        
        
    },
    computed: {
        

    }

})
app.mount("#app")