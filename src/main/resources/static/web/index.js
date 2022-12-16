const app=Vue.createApp({
    data(){
        return{
            email:"",
            password:"",
            emailCreate:"",
            passwordCreate:"",
            firstName:"",
            lastName:"",
            pantallaRegistro:false,
            pantallaSesion:true,
            alertCuentaExit:false
            
            
            
            
            
        }
    },
    methods:{
    iniciarSesion(){
            let email = this.email;
            let password = this.password
            let alertCuenta=this.alertCuentaExit
            
            axios.post('/api/login',`email=${email}&password=${password}`)
            .then(response => { window.location.href = 'http://localhost:8080/web/accounts.html'})
            .catch(error=>console.log("error put"))
                
            
        },
  
    postLogout(){
        axios.post('/api/logout').then(response => console.log('signed out!!!'))
    },
    registrar(){
        this.pantallaRegistro=true
        this.pantallaSesion=false
    },
    crearUsuario(){
        let email = this.emailCreate
        let password = this.passwordCreate
        let firstName =this.firstName
        let lastName= this.lastName

        axios.post('/api/clients',`firstName=${firstName}&lastName=${lastName}&email=${email}&password=${password}`).then(response => 
            axios.post('/api/login',`email=${email}&password=${password}`).then(response =>  window.location.href = 'http://localhost:8080/web/accounts.html'))
        
    }

    
    
    
    },
    created(){
        
        
        
        
    },
    computed: {

    }

})
app.mount("#app")