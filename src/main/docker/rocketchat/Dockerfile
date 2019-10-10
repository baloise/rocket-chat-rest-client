FROM rocketchat/base:8

ENV RC_VERSION latest

MAINTAINER buildmaster@rocket.chat

VOLUME /app/uploads

RUN set -x \
 && curl -SLf "https://releases.rocket.chat/${RC_VERSION}/download" -o rocket.chat.tgz \
 && curl -SLf "https://releases.rocket.chat/${RC_VERSION}/asc" -o rocket.chat.tgz.asc \
 && gpg --verify rocket.chat.tgz.asc \
 && tar -zxf rocket.chat.tgz -C /app \
 && rm rocket.chat.tgz rocket.chat.tgz.asc \
 && cd /app/bundle/programs/server \
 && npm install \
 && npm cache clear --force

USER rocketchat

WORKDIR /app/bundle

# needs a mongoinstance - defaults to container linking with alias 'mongo'
ENV MONGO_URL=mongodb://mongo:27017/rocketchat \
	MONGO_OPLOG_URL=mongodb://mongo:27017/local \
    HOME=/tmp \
    PORT=3000 \
    ROOT_URL=http://localhost:23737 \
    Accounts_AvatarStorePath=/app/uploads \
    Log_Level=2 \
    ADMIN_USERNAME=admin \
 	ADMIN_PASS=supersecret \
 	ADMIN_EMAIL=admin@example.com

EXPOSE 3000

CMD bash -c 'for i in `seq 1 10`; do node main.js && s=$? && break || s=$?; echo "Tried $i times. Waiting 5 secs..."; sleep 5; done; (exit $s)'
