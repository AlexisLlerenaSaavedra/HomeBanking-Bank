const app=Vue.createApp({
    data(){
        return{
            
            datos:[],
            cuentas:[],
            loans:[],
            primeraPantallaAccount:true,
            segundaPantallaAccount:false,
            terceraPantallaAccount:false,
            datosCliente:[],
            arrayTransacciones:[]

            
        }
    },
    methods:{

    
    getClients(){
            axios.get("/api/clients/current")
            .then (response=>{
                this.datos=response.data
                this.cuentas=this.datos.accounts
                this.loans=this.datos.loans
                console.log(this.loans)
                console.log(this.cuentas)
                console.log(this.datos)
                
                })    
    },
   
    modificarFecha(fecha){
        let fechaNueva=fecha.split("T")
        let fechaOtra=fechaNueva[0].split("-")
        return `${fechaOtra[2]}-${fechaOtra[1]}-${fechaOtra[0]}`

    },
    saldoTotal(){
        let saldoTodasCuentas=[];
        let resultado=0;
        this.cuentas.forEach(cuenta=>saldoTodasCuentas.push(cuenta.balance))
        saldoTodasCuentas.forEach(item=>resultado+=item)
        return resultado
    },

    recientesTransacciones(){
        let transacciones=[];
        let transaccionesfiltrado=[]
        this.cuentas.forEach(cuenta=>transacciones.push(cuenta.transactionDTOS))
        transacciones.map(transac=>transac.push(transaccionesfiltrado))
        console.log(transacciones)
        console.log(transaccionesfiltrado)
        this.arrayTransacciones=transacciones
        

    },



    mostrarTemplateAccount(){
        this.primeraPantallaAccount=false
        this.segundaPantallaAccount=true

    },
    atrasAccount(){
        this.primeraPantallaAccount=true
        this.segundaPantallaAccount=false
        this.terceraPantallaAccount=false
    },
    mostrarTemplateAccountTercero(){
        this.primeraPantallaAccount=false
        this.segundaPantallaAccount=false
        this.terceraPantallaAccount=true
    },
    cerrarSesion(){
        axios.post('/api/logout').then(response =>window.location.href = 'http://localhost:8080/web/index.html' )
    },
    createAccount(){
        axios.post('/api/clients/current/accounts').then(response=>console.log("bien"))
    },
    redigidirAPrestamos(){
        window.location.href='http://localhost:8080/web/loan-application.html'
    }

    
    },
    created(){
        this.getClients()
    },
    computed: {

    }

})
app.mount("#app")