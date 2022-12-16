const app=Vue.createApp({
    data(){
        return{
            
            datos:[],
            cuentas:[],
            primeraPantallaAccount:true,
            segundaPantallaAccount:false,
            
        }
    },
    methods:{
    getClients(){
            axios.get("/api/clients/1")
            .then (response=>{
                this.datos=response.data
                this.cuentas=this.datos.accounts
                console.log(response.data)
                console.log(this.cuentas)
                console.log(this.datos)
                
                })    
    },
    
    
    
    },
    created(){
        this.getClients()
    },
    computed: {

    }

})
app.mount("#app")