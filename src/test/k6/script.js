import {get} from './request.js'

export let options = {
    stages: [
        {duration: '20s', target: 100},
        {duration: '30s', target: 100},
        {duration: '10s', target: 0},
    ],
    thresholds: {
        // the rate of successful checks should be higher than 90%
        'checks{requestStatus:200}': ['rate>0.9'],
        // 95th percentile should respond under 5,100ms as the external service takes 5,000ms to return
        http_req_duration: ['p(95)<5100']
    }
}

export default function() {
    get({path: 'blocking'})
}