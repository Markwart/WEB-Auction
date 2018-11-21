CREATE TABLE "user_account" (
	"id" serial NOT NULL,
	"role" int NOT NULL,
	"email" character varying NOT NULL UNIQUE,
	"password" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT user_account_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "item" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"auction_end" TIMESTAMP NOT NULL,
	"starting_price" DECIMAL NOT NULL,
	"category_id" int NOT NULL,
	"year" int NOT NULL,
	"country_origin_id" int NOT NULL,
	"condition_id" int NOT NULL,
	"composition_id" int NOT NULL,
	"image" character varying NOT NULL,
	"text" TEXT NOT NULL,
	"seller_id" int NOT NULL,
	"status_auction" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT item_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "personal_data" (
	"id" int NOT NULL,
	"username" character varying NOT NULL,
	"first_name" character varying NOT NULL,
	"last_name" character varying NOT NULL,
	"address" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT personal_data_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "condition" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL UNIQUE,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT condition_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "category" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL UNIQUE,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT category_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "composition" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL UNIQUE,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT composition_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "shipping_method" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL UNIQUE,
	"delivery_time" character varying NOT NULL,
	"cost" DECIMAL NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT shipping_method_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "payment_method" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL UNIQUE,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT payment_method_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "feedback" (
	"id" serial NOT NULL,
	"item_id" int NOT NULL,
	"user_from_id" int NOT NULL,
	"user_whom_id" int NOT NULL,
	"communication" int NOT NULL,
	"shipping_time" int NOT NULL,
	"shipping_charges" int NOT NULL,
	"item_description" int NOT NULL,
	"comment" TEXT NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT feedback_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "auction_duration" (
	"id" serial NOT NULL,
	"min" int NOT NULL UNIQUE,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT auction_duration_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "bid" (
	"id" serial NOT NULL,
	"item_id" int NOT NULL,
	"price_bid" DECIMAL NOT NULL,
	"user_bid_id" int NOT NULL,
	"status_bid" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT bid_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_2_item" (
	"item_id" int NOT NULL,
	"user_id" int NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "message" (
	"id" serial NOT NULL,
	"item_id" int NOT NULL,
	"theme" character varying NOT NULL,
	"user_from_id" int NOT NULL,
	"user_whom_id" int NOT NULL,
	"text" TEXT NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT message_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "country_origin" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL UNIQUE,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT country_origin_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "auction_rule" (
	"id" serial NOT NULL,
	"index" character varying NOT NULL UNIQUE,
	"theme" character varying NOT NULL,
	"text" TEXT NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT auction_rule_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "admin_communication" (
	"id" serial NOT NULL,
	"theme" character varying NOT NULL,
	"user_from_id" int NOT NULL,
	"text" TEXT NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT admin_communication_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "deferred_bid" (
	"id" serial NOT NULL,
	"item_id" int NOT NULL,
	"price_bid" DECIMAL NOT NULL,
	"user_bid_id" int NOT NULL,
	"status_bid" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT deferred_bid_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "step_block" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL UNIQUE,
	"step_1" int NOT NULL,
	"step_2" int NOT NULL,
	"step_3" int NOT NULL,
	"step_4" int NOT NULL,
	"step_5" int NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT step_block_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);




ALTER TABLE "item" ADD CONSTRAINT "item_fk0" FOREIGN KEY ("category_id") REFERENCES "category"("id");
ALTER TABLE "item" ADD CONSTRAINT "item_fk1" FOREIGN KEY ("country_origin_id") REFERENCES "country_origin"("id");
ALTER TABLE "item" ADD CONSTRAINT "item_fk2" FOREIGN KEY ("condition_id") REFERENCES "condition"("id");
ALTER TABLE "item" ADD CONSTRAINT "item_fk3" FOREIGN KEY ("composition_id") REFERENCES "composition"("id");
ALTER TABLE "item" ADD CONSTRAINT "item_fk4" FOREIGN KEY ("seller_id") REFERENCES "user_account"("id");

ALTER TABLE "personal_data" ADD CONSTRAINT "personal_data_fk0" FOREIGN KEY ("id") REFERENCES "user_account"("id");






ALTER TABLE "feedback" ADD CONSTRAINT "feedback_fk0" FOREIGN KEY ("item_id") REFERENCES "item"("id");
ALTER TABLE "feedback" ADD CONSTRAINT "feedback_fk1" FOREIGN KEY ("user_from_id") REFERENCES "user_account"("id");
ALTER TABLE "feedback" ADD CONSTRAINT "feedback_fk2" FOREIGN KEY ("user_whom_id") REFERENCES "user_account"("id");


ALTER TABLE "bid" ADD CONSTRAINT "bid_fk0" FOREIGN KEY ("item_id") REFERENCES "item"("id");
ALTER TABLE "bid" ADD CONSTRAINT "bid_fk1" FOREIGN KEY ("user_bid_id") REFERENCES "user_account"("id");

ALTER TABLE "user_2_item" ADD CONSTRAINT "user_2_item_fk0" FOREIGN KEY ("item_id") REFERENCES "item"("id");
ALTER TABLE "user_2_item" ADD CONSTRAINT "user_2_item_fk1" FOREIGN KEY ("user_id") REFERENCES "user_account"("id");

ALTER TABLE "message" ADD CONSTRAINT "message_fk0" FOREIGN KEY ("item_id") REFERENCES "item"("id");
ALTER TABLE "message" ADD CONSTRAINT "message_fk1" FOREIGN KEY ("user_from_id") REFERENCES "user_account"("id");
ALTER TABLE "message" ADD CONSTRAINT "message_fk2" FOREIGN KEY ("user_whom_id") REFERENCES "user_account"("id");



ALTER TABLE "admin_communication" ADD CONSTRAINT "admin_communication_fk0" FOREIGN KEY ("user_from_id") REFERENCES "user_account"("id");

ALTER TABLE "deferred_bid" ADD CONSTRAINT "deferred_bid_fk0" FOREIGN KEY ("item_id") REFERENCES "item"("id");
ALTER TABLE "deferred_bid" ADD CONSTRAINT "deferred_bid_fk1" FOREIGN KEY ("user_bid_id") REFERENCES "user_account"("id");


