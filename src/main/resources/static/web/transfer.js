const app=Vue.createApp({
    data(){
        return{
            
            datos:[],
            cuentas:[],
            cuentaSeleccionada:[],
            templateSelectAccount:true,
            templateTransfer:false,
            misCuentas:false,
            cuentasTerceros:false,
            cuentaSele:"",
            montoEnviar:"",
            textTrasfer:"",
            numeroDeCuenta:"",
            numeroCuentaDestino:""

           
            
        }
    },
    methods:{
    getClients(){
            axios.get("/api/clients/current")
            .then (response=>{
                this.datos=response.data
                this.cuentas=this.datos.accounts
                console.log(this.datos)
                console.log(this.cuentas)

                
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
    selectCuenta(e){
        console.log(e.target.value)
        this.cuentaSeleccionada=e.target.value
        this.obtenerCuentaSeleccionada();
        this.templateTransfer=true
        this.templateSelectAccount=false




    },
    obtenerCuentaSeleccionada(){
        let arraySeleccionado= this.cuentas.find(cuenta=>cuenta.number==this.cuentaSeleccionada)
        this.cuentaSeleccionada=arraySeleccionado
        console.log(this.cuentaSeleccionada);
    },

    realizarTrasferencia(){
        Swal.fire({
            title: 'Esta seguro de hacer esta transferencia?',
            text: "No podras revertir esto!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si!'
          }).then((result) => {
            if (result.isConfirmed) {
              Swal.fire(
                'Enviado'
                
              )
              axios.post( "/api/transaction",`amount=${this.montoEnviar}&description=${this.textTrasfer}&origin=${this.cuentaSeleccionada.number}&destiny=${this.numeroDeCuenta}`)
              .then(respuesta=>{console.log("hehoo capooo")
              location. reload()})
            }
            
          })


    },
   
    numeroDeCuentaDestinataria(e){
        console.log(e.target.value)
    },
   
    
    cerrarSesion(){
        axios.post('/api/logout').then(response =>window.location.href = 'http://localhost:8080/web/index.html' )
    }

    
    },
    created(){
        this.getClients()
    },
    computed: {
        seleccionarCuentaEnviar(){
            console.log("tutu")
            if(this.cuentaSele=="cuentaTercero"){
                this.misCuentas=false
                this.cuentasTerceros=true
                
            }
            else if(this.cuentaSele=="cuentaPropia"){
                this.misCuentas=true
                this.cuentasTerceros=false
                

            }
            

        },
        arrayOpcionesMenosSelect(){
            let arrayOpciones= this.cuenta.filter(cuenta=>cuenta.number!=this.cuentaSeleccionada.number)
            console.log(arrayOpciones)
        },

    }

})
app.mount("#app")