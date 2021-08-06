    <template>
  <div class="review">
    <div id="potholesReview" v-for="potholes in $store.state.potholes" :key="potholes.id">
        <ul>
            <li>Date Created: {{ potholes.dateCreated }}</li>
            <li>Latitude: {{ potholes.latitude }}</li>
            <li>Longitude: {{ potholes.longitude }}</li>
            <li>Pothole ID: {{ potholes.potholeId }}</li>
            <li>Severity: {{ potholes.severity }}</li>
            <li>Current Status: {{ potholes.status }}</li>
            <form>
                <select v-model="potholes.status" onchange="alert('test')">
                    <option v-show="potholes.status != 'Scheduled'">Scheduled</option>
                    <option v-show="potholes.status != 'Repaired'">Repaired</option>
                    <option v-show="potholes.status != 'Inspected'">Inspected</option>
                    <option v-show="potholes.status != 'Reported'">Reported</option>
                </select>
                <br>
                <button type="submit" v-on:submit.prevent=" updateSchedule()">Submit New Status</button>
            </form>
        </ul>
        
    </div>
    
  </div>
</template>

<script>
import PotholeService from "../services/PotholeService" 
import moment from "moment";
export default {
    name: "review",
    components: {},
    data: () => ({
        potholes: [{dateCreated: null,
                    latitude: null,
                    longitude: null,
                    potholeId: "",
                    severity: "",
                    status: null,
                   }            
                ],
                message: ""

    }),
    displayReviews: function() {
            PotholeService.list().then( (response) => {
                this.$store.commit("ADD_POTHOLES", response.data);
			}).catch(error => {
				if(error.response.status == 400) {
					console.log(error.response.status);
				}
			});
        },
    methods: {
        updateSchedule() {
                const schedule = {
                    potholeId: this.potholes.id,
                    status: this.potholes.status,
                    dateInspected: null,
                    dateRepaired: null
                }

                if(schedule.status === 'Inspected'){
                    schedule.dateInspected = moment().format('YYYY-MM-DD')
                } else if (schedule.status === 'Repaired'){
                    schedule.dateRepaired = moment().format('YYYY-MM-DD')
                }


				PotholeService.updateSchedule(schedule).then(() => {
				console.log(schedule)
			}).catch(error => {
				if(error.response) {
					console.log('Error submitting new report.');
				} else if (error.request) {
					console.log("Error submitting new board. Server could not be reached.");
				} else {
					console.log("Error submitting new board. Request could not be created.");
				}
			})
			},
        
    },
    created() {
        PotholeService.list().then( (response) => {
				this.$store.commit("ADD_POTHOLES", response.data);
			}).catch(error => {
				if(error.response.status == 400) {
					console.log(error.response.status);
				}
			});
    }, 
    mounted() {
        
    }
}
</script>

<style>
.review {
    position: absolute;
    top: 9vh;
}
@media  only screen and (max-width: 768px) {
    .review {
        top: 13vh;
    }
}
</style>