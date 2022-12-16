const app=Vue.createApp({
    data(){
        return{
            clients:[],
            datos:{},
            firstName:"",
            lastName:"",
            email:"",
            alert:false,
        }
    },
    methods:{
        getClients(){
            axios.get("http://localhost:8080/clients")
            .then (response=>{
                this.datos=response.data
                this.clients=response.data._embedded.clients
                
        })
            
        },
        addClient(e){

            user={
              firstName:this.firstName,
              lastName:this.lastName,
              email:this.email.toLowerCase()
            }
    
            if(this.firstName == "" || this.lastName == "" || this.email==""){
    
              e.preventDefault()
              this.alert=true
            }
            else{
                this.postClient(user)
                location.reload()
                this.alert=false
            }
          },
        
        postClient(obj){
            axios.post("/clients",obj)
             .then(this.getClients())
    
          },
    },
    created(){
        this.getClients()
    }

})
app.mount("#app")