const app=Vue.createApp({
    data(){
        return{
            
            datos:[],
            cardsDebito:[],
            cardsCredito:[],
            cards:[],
            cardSelect:[],
            pantallaPrincipal:true,
            templateDebito:false,
            templateCredito:false,
            colorCard:""
            
            
        }
    },
    methods:{
    getClients(){
            axios.get("/api/clients/current")
            .then (response=>{
                this.datos=response.data
                this.cards=this.datos.cards
                this.cardsCredito=this.cards.filter(card=>card.type==="CREDITO")
                this.cardsDebito=this.cards.filter(card=>card.type==="DEBITO")

                
                
                console.log(this.cards)
                console.log(this.datos)
                console.log(this.cardsCredito)
                console.log(this.cardsDebito)

                
                })    
    },
    modificarFecha(fecha){
        let fechaNueva=fecha.split("T")
        let fechaOtra=fechaNueva[0].split("-")
        return `${fechaOtra[2]}-${fechaOtra[1]}-${fechaOtra[0]}`

    },
    mostrarColorCardCredi(e){
        console.log(e.target.value)
        let cardCredito=this.cardsCredito
        if(e.target.value==="TITANIUMCREDITO"){
            this.templateCredito=true;
            this.pantallaPrincipal=false;
            let cardMostrar=cardCredito.filter(item=>item.color=="TITANIUM"&&item.type=="CREDITO")
            console.log(cardMostrar)
            this.colorCard="colorTitanium"
            this.cardSelect=cardMostrar

        }
        else if(e.target.value==="SILVERCREDITO"){
            this.templateCredito=true;
            this.pantallaPrincipal=false;
            let cardMostrar=cardCredito.filter(item=>item.color=="SILVER"&&item.type=="CREDITO")
            console.log(cardMostrar)
            this.colorCard="colorSilver"
            this.cardSelect=cardMostrar
        }
        else if(e.target.value==="GOLDCREDITO"){
            this.templateCredito=true;
            this.pantallaPrincipal=false;
            let cardMostrar=cardCredito.filter(item=>item.color=="GOLD"&&item.type=="CREDITO")
            console.log(cardMostrar)
            this.colorCard="colorGold"
            this.cardSelect=cardMostrar
        }
         

    },
    mostrarColorCardDebi(e){
        console.log(e.target.value)
        let cardDebito=this.cardsDebito
        if(e.target.value==="TITANIUMDEBITO"){
            this.templateCredito=true;
            this.pantallaPrincipal=false;
            let cardMostrar=cardDebito.filter(item=>item.color=="TITANIUM"&&item.type=="DEBITO")
            console.log(cardMostrar)
            this.cardSelect=cardMostrar
            this.color="text-danger"
            this.colorCard="colorTitanium"


        }
        else if(e.target.value==="SILVERDEBITO"){
            this.templateCredito=true;
            this.pantallaPrincipal=false;
            let cardMostrar=cardDebito.filter(item=>item.color=="SILVER"&&item.type=="DEBITO")
            console.log(cardMostrar)
            this.colorCard="colorSilver"
            this.cardSelect=cardMostrar
        }
        else if(e.target.value==="GOLDDEBITO"){
            this.templateCredito=true;
            this.pantallaPrincipal=false;
            let cardMostrar=cardDebito.filter(item=>item.color=="GOLD"&&item.type=="DEBITO")
            console.log(cardMostrar)
            this.colorCard="colorGold"
            this.cardSelect=cardMostrar
        }

    },
    atrasCards(){
        this.pantallaPrincipal=true,
        this.templateDebito=false,
        this.templateCredito=false

    },
    cerrarSesion(){
        axios.post('/api/logout').then(response =>window.location.href = 'http://localhost:8080/web/index.html' )
        
    },
    eliminarCard(){
        axios.patch('/api/clients/current/cards?number=1157-1235-4564-3698')
        .then(response=>{
            console.log(response)
            respuestaTarjetaEliminada=response.data
            if(respuestaTarjetaEliminada=="Card disabled successfully"){
                console.log("estas eliminado baby")
            }
        })
        
    }


   
   
   
    

    
    },
    created(){
        this.getClients()
        console.log(this.cardSelect)
        
    },
    computed: {

    }

})
app.mount("#app")