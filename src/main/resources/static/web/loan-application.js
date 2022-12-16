const app=Vue.createApp({
    data(){
        return{
            
            datos:[],
            cuentas:[],
            datosLoans:[],
            cuentaSeleccionada:[],

            nameLoan:"",
            numeroDeLasCuotas:"0",
            
            
            numeroDeCuenta:"",
            valueRange:"0",
            max:null,
            loanSelect:[],
            pagosPorCuota:"0",
            idLoan:"",
            numeroDeCuenta:""

           
            
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
    getLoans(){
        axios.get("/api/loans")
            .then (response=>{
                this.datosLoans=response.data
                console.log(this.loanSelect)
                
                console.log(this.datosLoans)

                })    

    },
    elegirNombrePrestamo(e){
        console.log(e.target)
        console.log(e.target.value)
        nombrePrestamo=e.target.value
        if(nombrePrestamo=="Hipotecario"){
            let loan=this.datosLoans.filter(loan=>(loan.name=='Hipotecario'))
            let array=loan.map(item=>item.payments)
            this.loanSelect=array
            this.nameLoan=nombrePrestamo
            let maxPrestamo=loan.map(item=>item.maxAmount)
            this.max=maxPrestamo
            this.idLoan=14
            console.log(this.max)
            
        }
        else if(nombrePrestamo=="Personal"){
            let loan=this.datosLoans.filter(loan=>(loan.name=='Personal'))
            let array=loan.map(item=>item.payments)
            this.loanSelect=array
            this.nameLoan=nombrePrestamo
            let maxPrestamo=loan.map(item=>item.maxAmount)
            this.max=maxPrestamo
            this.idLoan=15
            console.log(this.max)

        }
        else if(nombrePrestamo=="Automotriz"){
            let loan=this.datosLoans.filter(loan=>(loan.name=='Automotriz'))
            let array=loan.map(item=>item.payments)
            this.loanSelect=array
            this.nameLoan=nombrePrestamo
            let maxPrestamo=loan.map(item=>item.maxAmount)
            this.max=maxPrestamo
            this.idLoan=16
            console.log(this.max)
        }
    },
    elegirLasCuotas(e){
        console.log(e.target.value)
        this.numeroDeLasCuotas=e.target.value
    },
    montoAmortizacion(){
        if(this.max==500000){
            this.pagosPorCuota=this.valueRange*1.2/this.numeroDeLasCuotas
            return this.valueRange*1.2
        }
        else if(this.max==100000){
            this.pagosPorCuota=this.valueRange*1.2/this.numeroDeLasCuotas

            return this.valueRange*1.1

        }
        else if(this.max==300000){
            this.pagosPorCuota=this.valueRange*1.2/this.numeroDeLasCuotas

            return this.valueRange*1.15

        }
        
    },
    realizarPrestamo(){
        Swal.fire({
            title: 'Esta seguro de hacer este prestamo?',
            text: "No podras revertir esto!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si!'
          }).then((result) => {
            if (result.isConfirmed) {
              Swal.fire(
                'Prestamo Exitoso',
                'Muchas Gracias'
                
              )
              axios.post("/api/loans",{
                "idLoan":this.idLoan,
                "amount":this.valueRange,
                "payments":this.numeroDeLasCuotas,
                "numberDestiny":this.numeroDeCuenta
            }
            )
            .then(respuesta=>{
                console.log("hechooo")
                window.location.href = 'http://localhost:8080/web/accounts.html'
            })
            }
            
          }
          )


    },
    
    solicitarPrestamo(){
        console.log(this.idLoan)
        console.log(this.valueRange)
        console.log(this.numeroDeLasCuotas)
        console.log(this.numeroDeCuenta)

        
        axios.post("/api/loans",{
            "idLoan":this.idLoan,
            "amount":this.valueRange,
            "payments":this.numeroDeLasCuotas,
            "numberDestiny":this.numeroDeCuenta
        })
        .then(respuesta=>console.log("hechooo"))
        

    },
   
    
    cerrarSesion(){
        axios.post('/api/logout').then(response =>window.location.href = 'http://localhost:8080/web/index.html' )
    }

    
    },
    created(){
        this.getClients()
        this.getLoans()
        

    },
    computed: {
        
        

    }

})
app.mount("#app")