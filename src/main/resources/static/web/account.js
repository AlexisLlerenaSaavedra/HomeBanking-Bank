const urlParams = new URLSearchParams(window.location.search);
const id= urlParams.get("id")


const app=Vue.createApp({
    data(){
        return{
            datos:[],
            transacciones:[],
            
            
        }
    },
    methods:{
    getClients(){
            axios.get(`/api/accounts/${id}`)
            .then (response=>{
                this.datos=response.data
                this.transacciones=this.datos.transactionDTOS
                
                
                console.log(this.datos)
                console.log(this.transacciones)
                })    
    },
    modificarFecha(fecha){
        let fechaNueva=fecha.split("T")
        let fechaOtra=fechaNueva[0].split("-")
        return `${fechaOtra[2]}-${fechaOtra[1]}-${fechaOtra[0]}`

    },
    sortArray(array){
        return array.sort((a,b)=>a.id - b.id)
    }

    },
    
    created(){
        this.getClients()
    },
   
    computed: {
        
        

    },
   

})
app.mount("#app")