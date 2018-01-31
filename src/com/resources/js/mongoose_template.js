import * as mongoose from 'mongoose';

const ##SCHEMA## = new mongoose.Schema({
  ##LIST##
});

const ##NAME## = mongoose.model('##NAME##', ##SCHEMA##);

export default ##NAME##;